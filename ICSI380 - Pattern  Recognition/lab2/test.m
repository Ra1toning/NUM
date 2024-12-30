
pixels = [16, 13, 12, 11, 21, 31, 61, 66, 163, 261, 113, 33, 326, 626, 161, 22];

avg_val = mean(pixels);

threshold = avg_val;
filtered_pixels = pixels >= threshold;
fprintf('filtered_pixels');
disp(filtered_pixels);

