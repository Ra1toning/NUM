clc
clear all
close all
pkg load image
myFolder = 'C:\Users\User\pattern recognition\lab1';
files = dir(fullfile(myFolder, '*.jpg'));

numFiles = length(files);

outputFolder = fullfile(myFolder, 'output');
mkdir(outputFolder);

img = imread(fullfile(myFolder, files(1).name));
imr = imresize(img, [400, 500]);
meanImg = double(imr);


for i = 2:numFiles
    img = imread(fullfile(myFolder, files(i).name));
    imr = imresize(img, [400, 500]);
    meanImg = meanImg + double(imr) / numFiles;
end


figure, imshow(uint8(meanImg));
imwrite(uint8(meanImg), fullfile(outputFolder, 'avg_img.jpg'));

for i = 1:numFiles
    img = imread(fullfile(myFolder, files(i).name));
    imr = imresize(img, [400, 500]);
    imgSub = uint8(meanImg - double(imr));
    figure, imshow(imgSub);
    imwrite(imgSub, fullfile(outputFolder, sprintf('img%d_sub.jpg', i)));
end

