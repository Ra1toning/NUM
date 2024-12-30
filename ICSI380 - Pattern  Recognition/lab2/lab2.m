pkg image load;

img = imread('lab1.tif');

nLevels = [256, 128, 64, 32, 16, 8, 4, 2];

figure;

for k = 1:length(nLevels)
    step = 256 / nLevels(k);

    quantized_img = floor(double(img) / step) * step;

    subplot(2, 4, k);
    imshow(quantized_img, []);
end

