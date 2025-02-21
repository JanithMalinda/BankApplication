# BankApplication 

## 🚀 Overview
The **BankApplication** is a Java-based application that simulates basic banking operations such as account creation, deposits, withdrawals, check balance, and transfers. It is designed using Java networking and supports multiple clients connecting to a central server.

## ✨ Features
- 🏦 **User account creation**
- 💰 **Deposit and withdrawal transactions**
- 🔄 **Multi-client support using sockets**
- 🔄 **Fund transfers between accounts**
- 🔒 **Secure communication between clients and the server**

## 🛠 Technologies Used
- ☕ **Java** (JDK 17 or higher recommended)
- 🔗 **Java Sockets** for client-server communication
- 🖥 **Eclipse IDE** (recommended for development)
- 📦 **Maven** (optional, for dependency management)

## ⚙️ Installation
### 📌 Prerequisites
Ensure you have the following installed:
- ☕ **Java Development Kit (JDK 17 or later)**
- 🖥 **Eclipse IDE (or any Java-supported IDE)**
- 🛠 **Git (optional, for cloning the repository)**

### 📥 Steps to Run the Application
1. **Clone the Repository** (if applicable):
   ```sh
   git clone https://github.com/janithmalinda/BankApplication.git
   cd BankApplication
   ```

2. **Open the Project in Eclipse**
   - Open **Eclipse**.
   - Go to `File` → `Open Projects from File System...` → Select the project folder.
   - Click `Finish`.

3. **Ensure the Correct JDK is Configured**
   - Go to `Window` → `Preferences` → `Java` → `Installed JREs`.
   - Make sure a **JDK** is selected, not just a **JRE**.

4. **Run the Server**
   - Locate `Server.java`.
   - Right-click and select **Run As** → **Java Application**.

5. **Run the Client**
   - Locate `Client.java`.
   - Right-click and select **Run As** → **Java Application**.
   - Repeat for multiple clients.

## 📌 Usage
1. 🖥 **The server starts and listens for incoming client connections.**
2. 👤 **Clients can connect and perform banking operations.**
3. 🔄 **The server handles transactions and maintains account details.**
4. 🔄 **Clients can transfer funds between accounts.**

## 🔮 Future Enhancements
- 🗄 **Implement a database for account storage (PostgreSQL recommended)**
- 🔐 **Add authentication (username/password system)**
- 🎨 **Improve UI with a JavaFX or web-based front end**
- 📜 **Generate account statements for users**



