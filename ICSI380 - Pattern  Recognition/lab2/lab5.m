clc;
clear all;

pkg load image;


img = imread('lab5.png');

if size(img, 3) == 3
    img_gray = rgb2gray(img);
else
    img_gray = img;
end

A = 150;
B = 230;

output_img = img_gray;
output_img(img_gray >= A & img_gray <= B) = 255;
output_img(img_gray < A | img_gray > B) = 0;

figure;

subplot(1, 2, 1);
imshow(img_gray);

subplot(1, 2, 2);
imshow(output_img);


