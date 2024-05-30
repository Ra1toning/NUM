import 'dart:convert';
import 'package:http/http.dart' as http;

class HttpService {
  final String baseUrl;
  HttpService({required this.baseUrl});
  Future<dynamic> getData(String endpoint, String? token) async {
    try {
      print('$baseUrl/$endpoint');
      final response = await http.get(
        Uri.parse('$baseUrl/$endpoint'),
        headers: token != null
            ? {
                'Authorization': 'Bearer $token',
              }
            : null,
      );
      if (response.statusCode == 200) {
        return json.decode(response.body);
      } else {
        throw Exception('Failed to load data');
      }
    } catch (e) {
      throw Exception('Failed to connect to the server');
    }
  }

  Future<http.Response> postData(
      String endpoint, String? token, dynamic data) async {
    try {
      print('$baseUrl/$endpoint');
      final response = await http.post(
        Uri.parse('$baseUrl/$endpoint'),
        body: json.encode(data),
        headers: {
          'Content-Type': 'application/json',
          if (token != null) 'Authorization': 'Bearer $token',
        },
      );
      if (response.statusCode == 200) {
        return response;
      } else {
        throw Exception('Failed to post data');
      }
    } catch (e) {
      throw Exception('Failed to connect to the server');
    }
  }

  Future<dynamic> putData(String endpoint, String? token, dynamic data) async {
    try {
      final response = await http.put(
        Uri.parse('$baseUrl/$endpoint'),
        body: json.encode(data),
        headers: {
          'Content-Type': 'application/json',
          if (token != null) 'Authorization': 'Bearer $token',
        },
      );
      if (response.statusCode == 200) {
        return json.decode(response.body);
      } else {
        throw Exception('Failed to put data');
      }
    } catch (e) {
      throw Exception('Failed to connect to the server');
    }
  }

  Future<dynamic> deleteData(
      String endpoint, String? token, dynamic data) async {
    try {
      final response = await http.delete(
        Uri.parse('$baseUrl/$endpoint'),
        headers: token != null ? {'Authorization': 'Bearer $token'} : null,
      );
      if (response.statusCode == 200) {
        return json.decode(response.body);
      } else {
        throw Exception('Failed to delete data');
      }
    } catch (e) {
      throw Exception('Failed to connect to the server');
    }
  }
}
