import os
import cv2
import torch
import torch.nn as nn
import numpy as np
from PIL import Image
from torchvision import transforms
import matplotlib.pyplot as plt

class UNet(nn.Module):
    def __init__(self, n_channels=3, n_classes=8):
        super().__init__()
        
        def double_conv(in_c, out_c):
            return nn.Sequential(
                nn.Conv2d(in_c, out_c, kernel_size=3, padding=1),
                nn.BatchNorm2d(out_c),
                nn.ReLU(inplace=True),
                nn.Conv2d(out_c, out_c, kernel_size=3, padding=1),
                nn.BatchNorm2d(out_c),
                nn.ReLU(inplace=True)
            )
        
        self.inc = double_conv(n_channels, 64)
        self.down1 = nn.Sequential(nn.MaxPool2d(2), double_conv(64, 128))
        self.down2 = nn.Sequential(nn.MaxPool2d(2), double_conv(128, 256))
        self.down3 = nn.Sequential(nn.MaxPool2d(2), double_conv(256, 512))
        self.down4 = nn.Sequential(nn.MaxPool2d(2), double_conv(512, 1024))
        
        self.up1 = nn.ConvTranspose2d(1024, 512, kernel_size=2, stride=2)
        self.up_conv1 = double_conv(1024, 512)
        self.up2 = nn.ConvTranspose2d(512, 256, kernel_size=2, stride=2)
        self.up_conv2 = double_conv(512, 256)
        self.up3 = nn.ConvTranspose2d(256, 128, kernel_size=2, stride=2)
        self.up_conv3 = double_conv(256, 128)
        self.up4 = nn.ConvTranspose2d(128, 64, kernel_size=2, stride=2)
        self.up_conv4 = double_conv(128, 64)
        self.outc = nn.Conv2d(64, n_classes, kernel_size=1)

    def forward(self, x):
        x1 = self.inc(x)
        x2 = self.down1(x1)
        x3 = self.down2(x2)
        x4 = self.down3(x3)
        x5 = self.down4(x4)
        
        x = self.up1(x5)
        x = self.up_conv1(torch.cat([x4, x], dim=1))
        x = self.up2(x)
        x = self.up_conv2(torch.cat([x3, x], dim=1))
        x = self.up3(x)
        x = self.up_conv3(torch.cat([x2, x], dim=1))
        x = self.up4(x)
        x = self.up_conv4(torch.cat([x1, x], dim=1))
        return self.outc(x)

INCLUDE_CLASSES = [0, 3, 4, 6, 7]  

CLASS_COLORS = {
    0: [255, 0, 0],       # Apartment (Red)
    3: [0, 255, 0],       # House (Green)
    4: [0, 0, 255],       # Large Vehicle (Blue)
    6: [255, 255, 0],     # Small Vehicle (Yellow)
    7: [255, 0, 255],     # Yard (Magenta)
}

def load_model(model_path, device, n_channels=3, n_classes=8):
    model = UNet(n_channels=n_channels, n_classes=n_classes).to(device)
    model.load_state_dict(torch.load(model_path, map_location=device))
    model.eval()
    return model

def preprocess_image(image_path, size=(512, 512)):
    image = Image.open(image_path).convert('RGB')
    transform = transforms.Compose([
        transforms.ToTensor(),
        transforms.Normalize(mean=[0.485, 0.456, 0.406], std=[0.229, 0.224, 0.225]),
    ])
    image_tensor = transform(image).unsqueeze(0)  # [C, H, W] -> [1, C, H, W]
    return image_tensor, image

def predict(model, image_tensor, device):
    image_tensor = image_tensor.to(device)
    with torch.no_grad():
        outputs = model(image_tensor)
    predictions = torch.argmax(outputs, dim=1).squeeze(0)  # [H, W]
    return predictions.cpu().numpy()


def save_result(image, predictions, output_path):
    image_np = np.array(image)
    segmentation_mask = np.zeros_like(image_np, dtype=np.uint8)
    unique_classes = np.unique(predictions)

    for class_idx in unique_classes:
        if class_idx in INCLUDE_CLASSES:
            color = CLASS_COLORS.get(class_idx, [0, 0, 0])  # Default to black if color not found
            segmentation_mask[predictions == class_idx] = color

    blended = cv2.addWeighted(image_np, 0.6, segmentation_mask, 0.4, 0)
    cv2.imwrite(output_path, cv2.cvtColor(blended, cv2.COLOR_RGB2BGR))

def detect_images(model_path, image_dir, output_dir, device):
    model = load_model(model_path, device)
    image_paths = [os.path.join(image_dir, f) for f in os.listdir(image_dir) if f.endswith(('.jpg', '.png', '.jpeg'))]

    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    for image_path in image_paths:
        print(f"Processing {image_path}...")
        image_tensor, image = preprocess_image(image_path)
        predictions = predict(model, image_tensor, device)

        output_path = os.path.join(output_dir, os.path.basename(image_path))
        save_result(image, predictions, output_path)
        print(f"Saved segmented overlay to {output_path}")

def main():
    model_path = "C:/Users/User/Desktop/unet/unet_final.pth"
    image_dir = "C:/Users/User/Desktop/unet/data/resized_images"
    output_dir = "C:/Users/User/Desktop/unet/output"
    device = torch.device('cuda' if torch.cuda.is_available() else 'cpu')
    detect_images(model_path, image_dir, output_dir, device)

if __name__ == "__main__":
    main()
