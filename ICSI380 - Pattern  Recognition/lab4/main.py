import os
import torch
from torch.utils.data import DataLoader
from torchvision import transforms
from unet.dataset import prepare_dataset, SegmentationDataset, COLOR2IDX
from unet.model import UNet
from unet.train import train_model
import torch.nn as nn
import torch.optim as optim

def main():
    base_dir = 'C:/Users/Newtech/OneDrive/Desktop/unet/data'
    img_dir = os.path.join(base_dir, 'images')
    mask_dir = os.path.join(base_dir, 'masks')
    resized_img_dir = os.path.join(base_dir, 'resized_images')
    resized_mask_dir = os.path.join(base_dir, 'resized_masks')

    IMAGE_SIZE = 512
    BATCH_SIZE = 4
    LEARNING_RATE = 1e-4
    NUM_EPOCHS = 50

    print("Preparing dataset...")
    prepare_dataset(img_dir, mask_dir, resized_img_dir, resized_mask_dir, (IMAGE_SIZE, IMAGE_SIZE))

    transform = transforms.Compose([
        transforms.ToTensor(),
        transforms.Normalize(mean=[0.485, 0.456, 0.406], std=[0.229, 0.224, 0.225])
    ])

    print("Creating dataloader...")
    dataset = SegmentationDataset(
        resized_img_dir,
        resized_mask_dir,
        transform=transform,
        color2idx=COLOR2IDX
    )
    train_loader = DataLoader(dataset, batch_size=BATCH_SIZE, shuffle=True, num_workers=2, pin_memory=True)

    print("Initializing model...")
    device = torch.device('cuda' if torch.cuda.is_available() else 'cpu')
    model = UNet(n_channels=3, n_classes=8).to(device)
    criterion = nn.CrossEntropyLoss()
    optimizer = optim.Adam(model.parameters(), lr=LEARNING_RATE)

    print("Starting training...")
    train_model(model, train_loader, criterion, optimizer, device, NUM_EPOCHS)

    torch.save(model.state_dict(), 'unet_final.pth')
    print("Training completed! Final model saved as 'unet_final.pth'.")

if __name__ == '__main__':
    main()
