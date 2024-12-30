import os
from PIL import Image
import numpy as np
import torch
from torch.utils.data import Dataset

def prepare_dataset(img_dir, mask_dir, output_img_dir, output_mask_dir, size=(512, 512)):
    os.makedirs(output_img_dir, exist_ok=True)
    os.makedirs(output_mask_dir, exist_ok=True)

    for img_name in os.listdir(img_dir):
        if img_name.endswith(('.jpg', '.png', '.jpeg')):
            output_img_path = os.path.join(output_img_dir, img_name)
            output_mask_path = os.path.join(output_mask_dir, os.path.splitext(img_name)[0] + '.png')

            if os.path.exists(output_img_path) and os.path.exists(output_mask_path):
                print(f"Skipping {img_name}, already resized.")
                continue

            img_path = os.path.join(img_dir, img_name)
            img = Image.open(img_path).convert('RGB')
            img_resized = img.resize(size, Image.Resampling.BILINEAR)
            img_resized.save(output_img_path)

            mask_name = os.path.splitext(img_name)[0] + '.png'
            mask_path = os.path.join(mask_dir, mask_name)
            if os.path.exists(mask_path):
                mask = Image.open(mask_path).convert('RGB')
                mask_resized = mask.resize(size, Image.Resampling.NEAREST)
                mask_resized.save(output_mask_path)
            else:
                print(f"Mask not found for image: {img_name}")

COLOR2IDX = {
    (211, 1, 159): 0,
    (71, 114, 126): 1,
    (0, 0, 0): 2,
    (192, 0, 224): 3,
    (38, 136, 115): 4,
    (32, 224, 224): 5,
    (97, 94, 243): 6,
    (159, 125, 238): 7
}

class SegmentationDataset(Dataset):
    def __init__(self, img_dir, mask_dir, transform=None, color2idx=None):
        self.img_dir = img_dir
        self.mask_dir = mask_dir
        self.transform = transform
        self.color2idx = color2idx
        self.images = [f for f in os.listdir(img_dir) if f.endswith(('.jpg', '.png', '.jpeg'))]

    def __len__(self):
        return len(self.images)

    def __getitem__(self, idx):
        img_name = self.images[idx]
        img_path = os.path.join(self.img_dir, img_name)
        mask_name = os.path.splitext(img_name)[0] + '.png'
        mask_path = os.path.join(self.mask_dir, mask_name)

        image = Image.open(img_path).convert('RGB')

        if os.path.exists(mask_path):
            mask = Image.open(mask_path).convert('RGB')
            mask = np.array(mask)

            mask_encoded = np.zeros((mask.shape[0], mask.shape[1]), dtype=np.int64)

            for color, idx_class in self.color2idx.items():
                matches = np.all(mask == color, axis=-1)
                mask_encoded[matches] = idx_class

            mask_tensor = torch.from_numpy(mask_encoded)
        else:
            mask_tensor = torch.zeros((image.size[1], image.size[0]), dtype=torch.long)
            print(f"Mask not found for image: {img_name}. Assigning background class.")

        if self.transform:
            image = self.transform(image)

        return image, mask_tensor
