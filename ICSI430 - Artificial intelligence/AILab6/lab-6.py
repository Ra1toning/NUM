import numpy as np
import pandas as pd

class Perceptron:
    def __init__(self, learning_rate, iterations):
        self.lr = learning_rate
        self.iterations = iterations
        self.errors = []
        self.weights = None

    def train(self, features, labels):
        if self.weights is None:
            self.weights = np.zeros(1 + features.shape[1])

        for epoch in range(self.iterations):
            error = 0
            for feature_set, target in zip(features, labels):
                update = self.lr * (target - self.predict(feature_set))
                self.weights[1:] += update * feature_set
                self.weights[0] += update
                error += int(update != 0)
            self.errors.append(error)

            print(f"Epoch {epoch + 1}: {error} misclassifications")

        return self

    def net_input(self, features):
        return np.dot(features, self.weights[1:]) + self.weights[0]

    def predict(self, features):
        return np.where(self.net_input(features) >= 0.0, 1, 0)

def calculate_accuracy(predictions, actual_labels):
    correct_predictions = np.sum(predictions == actual_labels)
    total_samples = len(actual_labels)
    accuracy = (correct_predictions / total_samples) * 100
    return accuracy

def main():
    iris_data = pd.read_csv('IRIS.csv', skiprows=1, header=None)
    features = iris_data.iloc[0:100, [0, 2]].values.astype(float)
    labels = np.where(iris_data.iloc[0:100, 4].values == 'Iris-setosa', 0, 1).astype(int)

    perceptron_classifier = Perceptron(learning_rate=0.001, iterations=6)
    perceptron_classifier.train(features, labels)

    predictions = perceptron_classifier.predict(features)
    accuracy = calculate_accuracy(predictions, labels)

    print("\nPerceptron Training Results:")
    print("----------------------------")
    print("Final Weights:")
    print(perceptron_classifier.weights)
    
    print("\nPredictions on Training Data:")
    print(predictions)
    
    print("\nAccuracy on Training Data: {:.2f}%".format(accuracy))

if __name__ == "__main__":
    main()
