pkg load image;

folder_path = 'C:/Users/User/pattern recognition/lab2/lab7';

image_files = dir(fullfile(folder_path, '*.png'));

num_images = numel(image_files);

for i = 1:min(4, num_images)

  img = imread(fullfile(folder_path, image_files(i).name));

  counts = zeros(256, 1);
  for pixel = 1:numel(img)
      counts(img(pixel) + 1) += 1;
  end

  cdf = cumsum(counts) / numel(img);

  img_eq = uint8(255 * cdf(double(img) + 1));

  subplot(4, 2, 2 * i - 1), imshow(img), title(['Original Image ', num2str(i)]);
  subplot(4, 2, 2 * i), imshow(img_eq), title(['Equalized Image ', num2str(i)]);
end

