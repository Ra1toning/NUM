import numpy as np

def calculate_metrics(predictions, masks, n_classes=8):
    confusion_matrix = np.zeros((n_classes, n_classes), dtype=np.int64)

    for true_class in range(n_classes):
        for pred_class in range(n_classes):
            confusion_matrix[true_class, pred_class] = np.logical_and(
                predictions == pred_class, masks == true_class
            ).sum()

    accuracy = np.diag(confusion_matrix).sum() / confusion_matrix.sum()
    precision = np.diag(confusion_matrix) / (confusion_matrix.sum(axis=0) + 1e-6)
    recall = np.diag(confusion_matrix) / (confusion_matrix.sum(axis=1) + 1e-6)
    return accuracy, precision.mean(), recall.mean()
