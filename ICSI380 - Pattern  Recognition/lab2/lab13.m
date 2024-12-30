pkg load image;

img = imread('lab13.png');
img = double(img)/255;


laplacian_kernel = [0 1 0; 1 -4 1; 0 1 0];

laplacian = imfilter(img, laplacian_kernel, 'same');

laplacian_scaled = 3.0 * laplacian;

sharpened_normal = img - laplacian;
sharpened_scaled = img - laplacian_scaled;

sharpened_normal = max(0, min(1, sharpened_normal));
sharpened_scaled = max(0, min(1, sharpened_scaled));

subplot(2, 3, 1);
imshow(img);
title('a) Original Image');

subplot(2, 2, 2);
imshow(laplacian_scaled, []);
title('c) Laplacian with scaling');

subplot(2, 2, 3);
imshow(sharpened_normal);
title('d) Sharpened without scaling');

subplot(2, 2, 4);
imshow(sharpened_scaled);
title('e) Sharpened with scaling');

