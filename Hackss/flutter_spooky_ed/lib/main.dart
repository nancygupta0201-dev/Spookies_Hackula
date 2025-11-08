import 'package:flutter/material.dart';
import 'package:webview_flutter/webview_flutter.dart';
import 'package:flutter/services.dart' show rootBundle;

void main() {
  runApp(const SpookyEdApp());
}

class SpookyEdApp extends StatelessWidget {
  const SpookyEdApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Spooky Ed',
      theme: ThemeData(
        primarySwatch: Colors.purple,
        useMaterial3: true,
      ),
      home: const SpookyEdHome(),
      debugShowCheckedModeBanner: false,
    );
  }
}

class SpookyEdHome extends StatefulWidget {
  const SpookyEdHome({super.key});

  @override
  State<SpookyEdHome> createState() => _SpookyEdHomeState();
}

class _SpookyEdHomeState extends State<SpookyEdHome> {
  late final WebViewController controller;
  bool canGoBack = false;

  @override
  void initState() {
    super.initState();
    _initializeWebView();
  }

  Future<void> _initializeWebView() async {
    controller = WebViewController()
      ..setJavaScriptMode(JavaScriptMode.unrestricted)
      ..setBackgroundColor(const Color(0x00000000))
      ..setNavigationDelegate(
        NavigationDelegate(
          onPageFinished: (String url) {
            _updateCanGoBack();
          },
          onNavigationRequest: (NavigationRequest request) {
            // Allow navigation within the app's HTML files
            if (request.url.startsWith('data:') || 
                request.url.contains('home.html') ||
                request.url.contains('about.html') ||
                request.url.contains('contact.html') ||
                request.url.contains('menu.html')) {
              return NavigationDecision.navigate;
            }
            return NavigationDecision.prevent;
          },
        ),
      );

    // Load the home page HTML
    await _loadHtmlAsset('assets/html/home.html');
  }

  Future<void> _loadHtmlAsset(String assetPath) async {
    final String htmlContent = await rootBundle.loadString(assetPath);
    final String baseUrl = assetPath.substring(0, assetPath.lastIndexOf('/') + 1);
    
    await controller.loadHtmlString(
      htmlContent,
      baseUrl: 'file:///android_asset/$baseUrl',
    );
  }

  Future<void> _updateCanGoBack() async {
    final bool canNavigateBack = await controller.canGoBack();
    setState(() {
      canGoBack = canNavigateBack;
    });
  }

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      onWillPop: () async {
        if (canGoBack) {
          await controller.goBack();
          return false;
        }
        return true;
      },
      child: Scaffold(
        body: SafeArea(
          child: WebViewWidget(controller: controller),
        ),
      ),
    );
  }
}
