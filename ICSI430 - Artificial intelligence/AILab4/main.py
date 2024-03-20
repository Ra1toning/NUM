from spam_classifier import initialize_data, spam_classifier

if __name__ == "__main__":
    spam_data, ham_data = initialize_data()

    spam_results = spam_classifier([spam_data, ham_data], "./spam_data/dev/spam/")
    ham_results = spam_classifier([spam_data, ham_data], "./spam_data/dev/ham/")

    del spam_data
    del ham_data

    confusion_matrix = [
        ["", "Predicted Ham", "Predicted Spam"],
        ["Actual Ham", ham_results[0] - ham_results[1], ham_results[1]],
        ["Actual Spam", spam_results[0] - spam_results[1], spam_results[1]],   
    ]

    for row in confusion_matrix:
        print(" {:<15}  {:<15}  {:<15} ".format(*row))

    true_positive = spam_results[1]
    false_positive = ham_results[1]
    true_negative = ham_results[0] - ham_results[1]
    false_negative = spam_results[0] - spam_results[1]

    accuracy = (true_positive + true_negative) / (true_positive + true_negative + false_positive + false_negative)
    precision = true_positive / (true_positive + false_positive)
    recall = true_positive / (true_positive + false_negative)
    f1_score = 2 * (precision * recall) / (precision + recall)

    measures_matrix = [
        ["", "Rate"],
        ["Accuracy", "{:.2%}".format(accuracy)],
        ["Precision", "{:.2%}".format(precision)],
        ["Recall", "{:.2%}".format(recall)],
        ["F1 Score", "{:.2}".format(f1_score)]
    ]

    for row in measures_matrix:
        print(" {:<32}  {:<25} ".format(*row))
