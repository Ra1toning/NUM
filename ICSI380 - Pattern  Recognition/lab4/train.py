import torch
from tqdm import tqdm
from unet.metrics import calculate_metrics

def train_model(model, train_loader, criterion, optimizer, device, num_epochs):
    model.train()
    print(f"Training on: {'GPU' if device.type == 'cuda' else 'CPU'}")

    for epoch in range(num_epochs):
        running_loss = 0.0
        running_accuracy = 0.0

        with tqdm(train_loader, desc=f'Epoch {epoch+1}/{num_epochs}') as pbar:
            for images, masks in pbar:
                images = images.to(device)
                masks = masks.to(device)

                optimizer.zero_grad()
                outputs = model(images)

                predictions = torch.argmax(outputs, dim=1)

                accuracy, precision, recall = calculate_metrics(predictions.cpu(), masks.cpu())
                running_accuracy += accuracy

                loss = criterion(outputs, masks)
                loss.backward()
                optimizer.step()

                running_loss += loss.item()
                pbar.set_postfix({'loss': loss.item(), 'accuracy': f"{accuracy:.4f}"})

        epoch_loss = running_loss / len(train_loader)
        epoch_accuracy = running_accuracy / len(train_loader)
        print(f'Epoch {epoch+1}/{num_epochs}, Loss: {epoch_loss:.4f}, Accuracy: {epoch_accuracy:.4f}')

        if (epoch + 1) % 5 == 0:
            torch.save(model.state_dict(), f'unet_epoch_{epoch+1}.pth')
            print(f'Model saved at epoch {epoch+1}')
