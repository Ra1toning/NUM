pkg load image;
img = imread('lab2.tif');

zoom_nearest = imresize(img, [1024 1024], 'nearest');
zoom_bilinear = imresize(img, [1024 1024], 'bilinear');

sizes = [128 64 32];
for i = 1:3
  shrunk_nearest{i} = imresize(zoom_nearest, [sizes(i) sizes(i)], 'nearest');
  shrunk_bilinear{i} = imresize(zoom_bilinear, [sizes(i) sizes(i)], 'bilinear');
end

for i = 1:3
  subplot(2, 3, i), imshow(shrunk_nearest{i});
  subplot(2, 3, i+3), imshow(shrunk_bilinear{i});
end

