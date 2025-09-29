package com.example.menucompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.menucompose.ui.theme.MenuComposeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MenuComposeTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("library") { LibraryScreen(navController) }
        composable("music") { MusicScreen(navController) }
        composable("movies") { MoviesScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {

    var fabCount by rememberSaveable { mutableStateOf(0) }

    Scaffold(
        topBar = { CustomTopBar(navController) },
        bottomBar = { CustomBottomBar(navController) },
        floatingActionButton = {
            FloatingActionButton(onClick = { fabCount++ }) {
                Text("+", fontSize = 24.sp)
            }
        },
        content = { padding ->
            CustomContent(padding, fabCount, navController)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(navController: NavHostController) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {  }) {
                Icon(Icons.Rounded.Menu, contentDescription = "Menú")
            }
        },
        title = { Text(text = "menu :b") },
        actions = {
            IconButton(onClick = { navController.navigate("library") }) {
                Icon(Icons.Rounded.Search, contentDescription = "Buscar")
            }

            IconButton(onClick = { navController.navigate("profile") }) {
                Icon(Icons.Outlined.AccountCircle, contentDescription = "Perfil")
            }
        }
    )
}

@Composable
fun CustomBottomBar(navController: NavHostController) {
    BottomAppBar {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(onClick = { navController.navigate("library") }) {
                Icon(Icons.Filled.Book, contentDescription = "Biblioteca")
            }
            IconButton(onClick = { navController.navigate("music") }) {
                Icon(Icons.Filled.MusicNote, contentDescription = "Música")
            }
            IconButton(onClick = { navController.navigate("movies") }) {
                Icon(Icons.Filled.Movie, contentDescription = "Películas")
            }
            IconButton(onClick = { navController.navigate("settings") }) {
                Icon(Icons.Filled.Settings, contentDescription = "Ajustes")
            }
        }
    }
}

@Composable
fun CustomContent(padding: PaddingValues, fabCount: Int, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp)
    ) {
        Text(text = "Contenido principal de la app", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Has presionado el botón flotante: $fabCount veces", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = { navController.navigate("music") }) {
            Text("Ir a Música")
        }
    }
}

@Composable
fun ProfileScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Perfil de Usuario", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(8.dp))
        Text("Nombre: Yadhira Ccori")
        Text("Correo: yadhira@gmail.com")
        Spacer(Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
    }
}

@Composable
fun LibraryScreen(navController: NavHostController) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Biblioteca", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(8.dp))
        Text("Contenido de la biblioteca...")

        Spacer(Modifier.height(16.dp))
        Button(onClick = { navController.navigate("home") }) {
            Text("Volver al inicio")
        }
    }
}

@Composable
fun MusicScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Música", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(8.dp))
        Text("Lista de canciones / playlists...")
        Spacer(Modifier.height(16.dp))
        Button(onClick = { navController.navigate("home") }) {
            Text("Volver al inicio")
        }
    }
}

@Composable
fun MoviesScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Películas", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(8.dp))
        Text("Listado de películas...")
        Spacer(Modifier.height(16.dp))
        Button(onClick = { navController.navigate("home") }) {
            Text("Volver al inicio")
        }
    }
}

@Composable
fun SettingsScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Ajustes", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(8.dp))
        Text("Opciones de configuración...")
        Spacer(Modifier.height(16.dp))
        Button(onClick = { navController.navigate("home") }) {
            Text("Volver al inicio")
        }
    }
}
