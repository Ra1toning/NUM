pkg load image

original = imread('lab15.png');
original = im2double(original);

function [Gx, Gy] = lab15(image)
    Sx = [-1 0 1; -2 0 2; -1 0 1];
    Sy = [-1 -2 -1; 0 0 0; 1 2 1];
    Gx = imfilter(image, Sx, 'replicate');
    Gy = imfilter(image, Sy, 'replicate');
endfunction

subplot(2, 4, 1);
imshow(original);
title('(a) Original Image');

% (b) Laplacian filter
laplacian_kernel = [0 1 0; 1 -4 1; 0 1 0];
laplacian = imfilter(original, laplacian_kernel, 'replicate');
subplot(2, 4, 2);
imshow(laplacian, []);
title('(b) Laplacian');

% (c) Sharpened image using Laplacian
sharpened_lap = original - laplacian;
subplot(2, 4, 3);
imshow(sharpened_lap, []);
title('(c) Sharpened (a+b)');

% (d) Sobel gradient
[Gx, Gy] = lab15(original);
sobel_gradient = sqrt(Gx.^2 + Gy.^2);
subplot(2, 4, 4);
imshow(sobel_gradient, []);
title('(d) Sobel Gradient');

% (e) Smoothed Sobel
avg_kernel = ones(5) / 25;
smoothed_sobel = imfilter(sobel_gradient, avg_kernel, 'replicate');
subplot(2, 4, 5);
imshow(smoothed_sobel, []);
title('(e) Smoothed Sobel');

% (f) Mask image
mask = sharpened_lap .* smoothed_sobel;
subplot(2, 4, 6);
imshow(mask, []);
title('(f) Mask (c*e)');

% (g) Sharpened using mask
sharpened_mask = original + mask;
subplot(2, 4, 7);
imshow(sharpened_mask, []);
title('(g) Sharpened (a+f)');

% (h) Power-law transformation
gamma = 0.7;
final_result = sharpened_mask .^ gamma;
subplot(2, 4, 8);
imshow(final_result, []);
title('(h) Final Result');

