import 'package:flutter/material.dart';
import 'package:foodorder_app/main/Item.dart';
import 'package:foodorder_app/main/MenuListItem.dart';

class OrderList extends StatelessWidget {
  final List<Item> selectedItems;

  const OrderList({super.key, required this.selectedItems});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Захиалга'),
      ),
      body: _buildContent(),
      bottomNavigationBar: BottomAppBar(
        child: Padding(
          padding: const EdgeInsets.symmetric(vertical: 8.0),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              SizedBox(
                width: 350,
                height: 50,
                child: TextButton(
                  onPressed: () => _confirmOrder(context),
                  style: TextButton.styleFrom(
                    backgroundColor: const Color.fromARGB(255, 89, 79, 235),
                    foregroundColor: Colors.white,
                  ),
                  child: const Text('Баталгаажуулах'),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  void _confirmOrder(BuildContext context) {
    if (selectedItems.isNotEmpty) {
      String orderDetails = '';
      for (var item in selectedItems) {
        orderDetails += '${item.name}, ';
      }
      orderDetails = orderDetails.substring(0, orderDetails.length - 2);
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(
          content: Text('Таны захиалга: $orderDetails'),
        ),
      );
    } else {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text('Та ямар нэгэн захиалга сонгоогүй байна!'),
        ),
      );
    }
  }

  Widget _buildContent() {
    return SafeArea(
      child: Column(
        children: [
          Expanded(
            child: _buildMenuList(),
          ),
        ],
      ),
    );
  }

  Widget _buildMenuList() {
    return ListView.separated(
      padding: const EdgeInsets.all(16),
      itemCount: selectedItems.length,
      separatorBuilder: (context, index) {
        return const SizedBox(
          height: 12,
        );
      },
      itemBuilder: (context, index) {
        final item = selectedItems[index];
        return _buildMenuItem(
          item: item,
        );
      },
    );
  }

  Widget _buildMenuItem({
    required Item item,
  }) {
    return Dismissible(
      key: Key(item.uid),
      onDismissed: (direction) {
        selectedItems.remove(item);
      },
      background: ClipRRect(
        borderRadius: BorderRadius.circular(10.0),
        child: Container(
          color: const Color.fromARGB(255, 54, 82, 244),
          alignment: Alignment.centerRight,
          padding: const EdgeInsets.symmetric(horizontal: 20.0),
          child: const Icon(
            Icons.delete,
            color: Colors.white,
          ),
        ),
      ),
      child: MenuListItem(
        name: item.name,
        price: item.formattedTotalItemPrice,
        photoProvider: item.imageProvider,
      ),
    );
  }
}
