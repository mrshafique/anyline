# Asset Image Upload with API Retry

This project demonstrates upload asset images. In case of an API error, the application retries uploading the same image until it receives a successful response. This functionality is achieved using the Koin library for dependency injection and the Retrofit library for making API calls.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [Configuration](#configuration)
- [Dependencies](#dependencies)
- [Contributing](#contributing)
- [License](#license)

## Prerequisites
Before getting started, make sure you have the following:
- Android Studio or a compatible development environment.
- A valid API endpoint for uploading images.
- Basic knowledge of Kotlin, Retrofit, and Koin.

## Installation
1. Clone this repository to your local machine:
   ```
   git clone https://github.com/mrshafique/anyline.git
   ```
2. Open the project in Android Studio or your preferred IDE.
3. Build and run the application on your Android device or emulator.

## Usage
1. Launch the application on your device.
2. Navigate to the image upload screen.
3. Select an image from your device.
4. Tap the "Upload" button to start the image upload process.
5. If the API returns a success response, the image is considered uploaded.
6. If an API error occurs, the application will retry uploading the same image until it receives a successful response.

## Configuration
### Dependencies
This project uses the following libraries:
- [Koin](https://insert-koin.io/): For dependency injection.
- [Retrofit](https://square.github.io/retrofit/): For making API calls.
Ensure that you have the appropriate dependencies added to your project's Gradle files.

## Contributing
Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
