import 'dart:io';
import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';

class ImageInput extends StatefulWidget {
  const ImageInput({super.key, required this.OnTakeImg});
  final void Function(File image) OnTakeImg;

  @override
  State<ImageInput> createState() => _ImageInputState();
}

class _ImageInputState extends State<ImageInput> {
  File? takenImage;
  void _takePhoto() async {
    final picker = ImagePicker();
    final pickedImage =
        await picker.pickImage(source: ImageSource.camera, maxWidth: 500);
    if (pickedImage == null) {
      debugPrint('take photo error');
      return;
    }
    takenImage = File(pickedImage.path);

    setState(() {});
    widget.OnTakeImg(takenImage!);
  }

  @override
  Widget build(BuildContext context) {
    Widget content =
        TextButton(onPressed: _takePhoto, child: Text('Take photo'));
    if (takenImage != null) {
      content = Image.file(
        takenImage!,
        fit: BoxFit.cover,
        width: double.infinity,
      );
    }
    return Container(
      decoration: BoxDecoration(
          border: Border.all(
              width: 1,
              color: Theme.of(context).colorScheme.primary.withOpacity(0.5))),
      height: 300,
      width: double.infinity,
      alignment: Alignment.center,
      child: content,
    );
  }
}
