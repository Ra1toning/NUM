import os
import cv2
import numpy as np
from skimage.feature import local_binary_pattern
from sklearn.preprocessing import StandardScaler
from sklearn.cluster import KMeans
import pandas as pd

input_folder = r'C:\Users\User\pattern recognition\lab3\data'
output_folder = r'C:\Users\User\pattern recognition\lab3\annotation'

if not os.path.exists(output_folder):
    os.makedirs(output_folder)

denomination_logic = {
    (740, 742, 2.08, 2.09): 5000,
    (630, 634, 1.94, 1.96): 100,
    (705, 707, 1.99, 2.00): 20000,
    (726, 730, 2.09, 2.10): 500,
    (696, 698, 2.21, 2.22): 10000,
    (696, 698, 2.24, 2.25): 1000,
}

for filename in os.listdir(input_folder):
    if filename.lower().endswith(('.png', '.jpg', '.jpeg')):
        image_path = os.path.join(input_folder, filename)
        image = cv2.imread(image_path)
        gray_image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
        edges = cv2.Canny(gray_image, threshold1=50, threshold2=150)
        _, threshold_image = cv2.threshold(gray_image, 200, 255, cv2.THRESH_BINARY_INV)

        contours, _ = cv2.findContours(threshold_image, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

        features = []
        bounding_boxes = []

        for contour in contours:
            x, y, w, h = cv2.boundingRect(contour)
            if w < 100 or h < 100:  
                continue
            bounding_boxes.append((x, y, w, h))
            roi = gray_image[y:y+h, x:x+w]
            lbp = local_binary_pattern(roi, 24, 3, method="uniform")
            lbp_hist, _ = np.histogram(lbp.ravel(), bins=np.arange(0, 27), range=(0, 26), density=True)
            color_mean = cv2.mean(image[y:y+h, x:x+w])[:3]
            features.append([w, h, w/h] + list(color_mean) + lbp_hist.tolist())  

        X = np.array(features)
        scaler = StandardScaler()
        X_scaled = scaler.fit_transform(X)

        kmeans = KMeans(n_clusters=6, random_state=0)
        kmeans.fit(X_scaled)
        labels = kmeans.labels_

        cluster_data = pd.DataFrame({'Cluster': labels, 'Width': X[:, 0], 'Height': X[:, 1], 'AspectRatio': X[:, 2]})
        cluster_summary = cluster_data.groupby('Cluster').mean()

        cluster_to_denom = {}
        for cluster, row in cluster_summary.iterrows():
            width, aspect_ratio = row['Width'], row['AspectRatio']
            for (w_min, w_max, ar_min, ar_max), denom in denomination_logic.items():
                if w_min <= width <= w_max and ar_min <= aspect_ratio <= ar_max:
                    cluster_to_denom[cluster] = denom
                    break

        annotated_image = image.copy()
        total_amount = 0

        for i, (x, y, w, h) in enumerate(bounding_boxes):
            cluster = labels[i]
            denomination = cluster_to_denom.get(cluster, 0)
            total_amount += denomination
            label = f"{denomination}MNT"

            cv2.rectangle(annotated_image, (x, y), (x+w, y+h), (0, 255, 0), 2)
            
            text_x = x + 10  
            text_y = y + h - 10 
            cv2.putText(annotated_image, label, (text_x, text_y), cv2.FONT_HERSHEY_SIMPLEX, 1, (255, 255, 255), 3)

        cv2.putText(annotated_image, f"Total Amount: {total_amount}MNT", (10, 30),
                    cv2.FONT_HERSHEY_SIMPLEX, 1.2, (0, 0, 255), 3) 

        output_path = os.path.join(output_folder, f"annotated_{filename}")
        cv2.imwrite(output_path, annotated_image)

        print(f"Annotated: {filename}, Total Amount: {total_amount} MNT")
