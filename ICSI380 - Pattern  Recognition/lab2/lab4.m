pkg load image;

img_gray = imread('lab3.png');

r1 = double(min(img_gray(:)));
r2 = double(max(img_gray(:)));

s1 = 0;
s2 = 255;

output_img = uint8((double(img_gray) - r1) * (s2 - s1) / (r2 - r1) + s1);
output_img2 = uint8(((double(img_gray) - r1) / (r2 - r1)) * 255);

threshold_value = 110;
threshold_img = img_gray > threshold_value;

figure;

subplot(2,2,1);
imshow(img_gray);

subplot(2,2,2);
imshow(output_img);

subplot(2,2,3);
imshow(threshold_img);

subplot(2,2,4)
imshow(output_img2)
