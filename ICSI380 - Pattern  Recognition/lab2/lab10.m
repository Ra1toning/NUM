pkg load image;

img = imread('lab9.png');
kernel_sizes = [3, 5, 9, 15, 35];

results = {};
for k = 1:length(kernel_sizes)
    kernel_size = kernel_sizes(k);
    kernel = ones(kernel_size, kernel_size) / (kernel_size^2);
    smoothed_img = imfilter(img, kernel, 'symmetric');
    results{k} = smoothed_img;
end

figure;
subplot(3, 2, 1), imshow(img), title('Original Image');
subplot(3, 2, 2), imshow(results{1}), title('3x3 Mask');
subplot(3, 2, 3), imshow(results{2}), title('5x5 Mask');
subplot(3, 2, 4), imshow(results{3}), title('9x9 Mask');
subplot(3, 2, 5), imshow(results{4}), title('15x15 Mask');
subplot(3, 2, 6), imshow(results{5}), title('35x35 Mask');

