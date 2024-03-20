import os
from collections import defaultdict
from math import log

class DocumentStats:
    def __init__(self):
        self.total_words = 0
        self.word_counts = defaultdict(int)

def read_words_from_file(file_path):
    with open(file_path, 'rb') as file:
        try:
            return file.read().decode('utf-8', errors='replace').split()
        except Exception as e:
            print(f"An unexpected error occurred in read_words_from_file: {e}")
            return []

def calculate_document_probabilities(words, word_probs, total_word_count):
    return sum(log(word_probs.get(word, 1 / total_word_count)) for word in words)

def initialize_document_stats(directory, alpha=1):
    document_stats = DocumentStats()
    files_list = os.listdir(directory)

    for file in files_list:
        file_path = os.path.join(directory, file)
        words = read_words_from_file(file_path)

        document_stats.total_words += len(words)
        for word in words:
            word = word.lower()
            document_stats.word_counts[word] += 1

    unique_words = len(document_stats.word_counts)
    for word in document_stats.word_counts:
        document_stats.word_counts[word] = (document_stats.word_counts[word] + alpha) / (document_stats.total_words + alpha * unique_words)

    return document_stats

def initialize_data():
    spam_dir = "./spam_data/train/spam/"
    ham_dir = "./spam_data/train/ham/"

    spam_data = initialize_document_stats(spam_dir)
    ham_data = initialize_document_stats(ham_dir)

    return spam_data, ham_data

def spam_classifier(document_stats, path):
    files_list = os.listdir(path)
    total_docs = 0
    detected_spam_count = 0

    for file in files_list:
        file_path = os.path.join(path, file)
        words = read_words_from_file(file_path)
        total_docs += 1

        prob_spam = calculate_document_probabilities(words, document_stats[0].word_counts, document_stats[0].total_words)
        prob_ham = calculate_document_probabilities(words, document_stats[1].word_counts, document_stats[1].total_words)

        if prob_spam > prob_ham:
            detected_spam_count += 1

    return total_docs, detected_spam_count