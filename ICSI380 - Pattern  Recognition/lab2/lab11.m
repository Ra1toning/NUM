pkg load image;


img = imread('lab11.png');


subplot(1, 3, 1), imshow(img), title('Original Image');

kernel_size = 15;
kernel = ones(kernel_size, kernel_size) / (kernel_size^2);
smoothed_img = imfilter(img, kernel, 'symmetric');
subplot(1, 3, 2), imshow(smoothed_img), title('15x15 Averaging Filter');

threshold_value = 100;
binary_img = smoothed_img > threshold_value;
subplot(1, 3, 3), imshow(binary_img), title('Thresholded Image');

