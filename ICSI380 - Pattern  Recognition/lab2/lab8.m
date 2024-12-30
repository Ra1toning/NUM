pkg load image
img = imread('lab7.png');
if size(img, 3) == 3
    img = rgb2gray(img);
end

function output = local_hist_eq(img, window_size)
    [rows, cols] = size(img);
    output = zeros(rows, cols, 'uint8');
    pad = floor(window_size / 2);
    padded = padarray(img, [pad pad], 'replicate');

    for i = 1:rows
        for j = 1:cols
            neighborhood = padded(i:i + window_size - 1, j:j + window_size - 1);
            hist_counts = zeros(1, 256);
            for k = 1:numel(neighborhood)
                hist_counts(neighborhood(k) + 1) += 1;
            end
            cum_hist = cumsum(hist_counts);
            cum_hist_norm = cum_hist * 255 / cum_hist(end);
            output(i, j) = cum_hist_norm(img(i, j) + 1);
        end
    end
end

window_size = 3;
result = local_hist_eq(img, window_size);

figure;
subplot(1, 3, 1); imshow(img); title('Original Image');
subplot(1, 3, 2); imshow(histeq(img)); title('Global Histogram Equalization');
subplot(1, 3, 3); imshow(result); title('Local Histogram Equalization');

