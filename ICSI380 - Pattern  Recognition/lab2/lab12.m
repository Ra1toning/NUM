pkg load image;

img = imread('lab12.tif');

subplot(1, 3, 1), imshow(img), title('Original Image with Noise');

avg_kernel = ones(3, 3) / 9;
avg_filtered_img = imfilter(img, avg_kernel, 'symmetric');
subplot(1, 3, 2), imshow(avg_filtered_img), title('3x3 Averaging Filter');

median_filtered_img = medfilt2(img, [3, 3]);
subplot(1, 3, 3), imshow(median_filtered_img), title('3x3 Median Filter');

