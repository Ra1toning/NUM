pkg load image;

original_img = imread('lab14.png');

sigma = 2;
gaussian_filter = fspecial('gaussian', [5, 5], sigma);

blurred_img = imfilter(original_img, gaussian_filter, 'replicate');

unsharp_mask = original_img - blurred_img;

sharpened_img = original_img + unsharp_mask;

k = 1.5;
highboost_img = original_img + k * unsharp_mask;

figure;
subplot(5, 1, 1), imshow(original_img), title('Original Image');
subplot(5, 1, 2), imshow(blurred_img), title('Blurred Image');
subplot(5, 1, 3), imshow(unsharp_mask, []), title('Unsharp Mask');
subplot(5, 1, 4), imshow(sharpened_img), title('Unsharp Masking Result');
subplot(5, 1, 5), imshow(highboost_img), title('High-boost Filtering Result');

