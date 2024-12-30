pkg load image;


original_image = imread('castle.png');

[rows, cols] = size(original_image);
mask = zeros(rows, cols);
mask(5:100, 80:125) = 255;

or_mask = ones(rows, cols) * 255;
or_mask(5:100, 80:125) = 0;

masked_img = bitand(original_image, uint8(mask));
or_masked_img = bitor(original_image, uint8(or_mask));

figure;
subplot(2, 3, 1), imshow(original_image), title('Original Image');
subplot(2, 3, 2), imshow(uint8(mask)), title('AND Mask');
subplot(2, 3, 3), imshow(masked_img), title('AND Result');
subplot(2, 3, 4), imshow(original_image), title('Original Image (OR)');
subplot(2, 3, 5), imshow(uint8(or_mask)), title('OR Mask');
subplot(2, 3, 6), imshow(or_masked_img), title('OR Result');

