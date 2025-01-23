# NeoExcalibur

**NeoExcalibur** is a Java-based blockchain implementation that demonstrates the fundamental concepts of blockchain technology, including wallet creation, transaction processing, and block mining. This project serves as an educational tool for understanding the mechanics of cryptocurrencies and distributed ledger systems.

## Features

- **Wallet Generation**: Create unique wallets with public and private key pairs for secure transactions.
- **Transaction Management**: Facilitate transactions between wallets, including signature generation and verification to ensure authenticity.
- **Block Mining**: Implement a proof-of-work consensus mechanism to mine new blocks and add them to the blockchain.
- **Blockchain Validation**: Verify the integrity of the blockchain by checking hashes and ensuring no tampering has occurred.

## Installation

To set up **NeoExcalibur** locally, follow these steps:

1. **Clone the repository**:

   ```bash
   git clone https://github.com/Sam-Orion/NeoExcalibur.git
   ```

2. **Navigate to the project directory**:

   ```bash
   cd NeoExcalibur
   ```

3. **Build the project using Gradle**:

   Ensure you have [Gradle](https://gradle.org/install/) installed. Then, run:

   ```bash
   gradle build
   ```

## Usage

To run the **NeoExcalibur** application:

1. **Execute the main class**:

   ```bash
   java -cp build/classes/java/main noobchain.Main
   ```

2. **Observe the console output**:

   The application will generate wallets, perform transactions, mine blocks, and display the blockchain status in the console.

## Contributing

Contributions to **NeoExcalibur** are welcome. To contribute:

1. **Fork the repository**.
2. **Create a new branch**:

   ```bash
   git checkout -b feature/your-feature-name
   ```

3. **Make your changes**.
4. **Commit your changes**:

   ```bash
   git commit -m "Add your commit message here"
   ```

5. **Push to your branch**:

   ```bash
   git push origin feature/your-feature-name
   ```

6. **Create a Pull Request** detailing your changes.

## License

This project is licensed under the MIT License. See the [LICENSE](https://github.com/Sam-Orion/NeoExcalibur/blob/main/LICENSE) file for more details.

## Acknowledgements

- Inspired by educational resources on blockchain technology and Java programming.
- Utilizes the Bouncy Castle library for cryptographic functions.
