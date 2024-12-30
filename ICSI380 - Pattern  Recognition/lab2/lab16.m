
original_img = imread('lab16.png');
img = im2double(original_img);

[M, N] = size(original_img);

F = fftshift(fft2(original_img));

freq_points = [
    M/2-80, N/2-30;
    M/2+80, N/2+30;
    M/2-80, N/2+30;
    M/2+80, N/2-30;
    M/2-40, N/2-30;
    M/2+40, N/2-30;
    M/2-40, N/2+30;
    M/2+40, N/2+30;
];

notch_filter = ones(M, N);
D0 = 10;
for k = 1:size(freq_points, 1)
    u0 = freq_points(k, 1);
    v0 = freq_points(k, 2);
    for u = 1:M
        for v = 1:N
            D1 = sqrt((u - u0)^2 + (v - v0)^2);
            D2 = sqrt((u + u0 - M)^2 + (v + v0 - N)^2);
            notch_filter(u, v) = notch_filter(u, v) * (1 - exp(-(D1^2)/(2*D0^2))) * (1 - exp(-(D2^2)/(2*D0^2)));
        endfor
    endfor
endfor

F_filtered = F .* notch_filter;

filtered_img = real(ifft2(ifftshift(F_filtered)));

figure;
subplot(2, 2, 1);
imshow(original_img, []);
title('Original Image');

subplot(2, 2, 2);
imshow(log(1 + abs(F)), []);
title('Fourier Spectrum');

subplot(2, 2, 3);
imshow(log(1 + abs(F_filtered)), []);
title('Filtered Fourier Spectrum');

subplot(2, 2, 4);
imshow(filtered_img, []);
title('Filtered Image');

