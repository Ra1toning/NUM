clc;
clear all;
pkg load image;

img = imread('lab6.png');

figure;
subplot(3, 3, 1);
imshow(img);
title('Original Image');
[rows, cols] = size(img);


for bit = 1:8
    bit_plane = zeros(rows, cols);

    for i = 1:rows
        for j = 1:cols
            pixel = img(i, j);
            bit_plane(i, j) = mod(floor(pixel / (2^(bit - 1))), 2);
        end
    end

    subplot(3, 3, bit + 2);
    imshow(logical(bit_plane));
    title(['Bit Plane ', num2str(bit)]);
end

