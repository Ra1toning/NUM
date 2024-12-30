clc;
clear all;

pkg load image;

img = imread('lab6.png');

figure;
subplot(3, 3, 1);
imshow(img);
title('Original Image');

[rows, cols] = size(img);

for bit = 0:7
  bit_plane = bitget(img, bit+1);

  subplot(3, 3, bit+2);
  imshow(logical(bit_plane));

endfor

