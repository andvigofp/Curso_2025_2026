# Gestor de Usuarios con API REST

## 📱 Integración con Retrofit

Esta aplicación ahora incluye conectividad con API REST externa usando **Retrofit**, permitiendo operaciones CRUD completas contra un servidor remoto.

---

## 🏗️ Arquitectura Implementada

### Estructura de Capas

```
app/
├── data/
│   ├── remote/
│   │   ├── api/
│   │   │   └── UsuarioApiService.kt      # Definición de endpoints
│   │   ├── dto/
│   │   │   └── UsuarioDto.kt             # Objetos de transferencia de datos
│   │   └── RetrofitClient.kt             # Configuración de Retrofit
│   └── repository/
│       └── UsuarioRepository.kt          # Capa de abstracción de datos
├── model/
│   └── Usuario.kt                        # Modelo de dominio
├── viewmodel/
│   └── UsuarioViewModel.kt               # Lógica de negocio y estado
├── ui/
│   ├── screens/
│   │   ├── ListaUsuariosScreen.kt        # Pantalla principal
│   │   └── FormularioUsuarioScreen.kt    # Formulario CRUD
│   └── state/
│       └── UiState.kt                    # Estados de UI (Loading, Success, Error)
└── navigation/
    └── NavGraph.kt                       # Navegación entre pantallas
```

---

## 🔧 Tecnologías y Dependencias

### Retrofit & Networking
- **Retrofit 2.9.0** - Cliente HTTP type-safe para Android
- **Gson Converter** - Serialización/deserialización JSON
- **OkHttp 4.12.0** - Cliente HTTP eficiente
- **Logging Interceptor** - Logs de peticiones y respuestas

### Coroutines
- **Kotlinx Coroutines** - Operaciones asíncronas
- **Lifecycle ViewModel KTX** - Integración con ViewModels

### Compose & UI
- **Navigation Compose** - Navegación declarativa
- **Material 3** - Diseño moderno
- **ViewModel Compose** - Integración con Compose

---

## 🌐 API REST Configurada

### API Base
**JSONPlaceholder** (https://jsonplaceholder.typicode.com/)
- API de prueba gratuita
- No requiere autenticación
- Simula operaciones CRUD

### Endpoints Implementados

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/users` | Obtener todos los usuarios |
| GET | `/users/{id}` | Obtener usuario por ID |
| POST | `/users` | Crear nuevo usuario |
| PUT | `/users/{id}` | Actualizar usuario |
| DELETE | `/users/{id}` | Eliminar usuario |

---

## 🎯 Características Implementadas

### ✅ Modo Dual de Operación

La app soporta dos modos:

1. **Modo Local** (por defecto)
   - Datos almacenados en memoria
   - Operaciones inmediatas
   - Datos de ejemplo precargados

2. **Modo API REST**
   - Conecta con JSONPlaceholder
   - Operaciones asíncronas
   - Estados de carga y error
   - Manejo de errores de red

### ✅ Gestión de Estados

```kotlin
sealed class UiState<out T> {
    object Idle : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}
```

### ✅ UI Adaptativa

- **Loading indicators** durante operaciones
- **Snackbars** para feedback de operaciones
- **Mensajes de error** descriptivos
- **Switch toggle** para cambiar entre modos
- **Botón refresh** para recargar datos

---

## 📦 DTOs (Data Transfer Objects)

### UsuarioDto
```kotlin
data class UsuarioDto(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val address: AddressDto?
)
```

### Conversión DTO → Model
El repositorio se encarga de convertir automáticamente los DTOs de la API al modelo de dominio `Usuario`.

---

## 🔄 Flujo de Datos

```
[UI Screen] 
    ↓ (User Action)
[ViewModel]
    ↓ (suspend function)
[Repository]
    ↓ (HTTP Request)
[Retrofit + API Service]
    ↓ (Network Call)
[API REST Server]
    ↓ (Response)
[DTO → Model Conversion]
    ↓ (UiState Update)
[UI Update]
```

---

## 🛠️ Configuración de Retrofit

### RetrofitClient.kt

```kotlin
object RetrofitClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()
    
    val usuarioApiService: UsuarioApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UsuarioApiService::class.java)
}
```

---

## 📝 Uso de la Aplicación

### Cambiar a Modo API
1. Abrir la aplicación
2. En la pantalla principal, usar el **Switch** en el TopBar
3. Los datos se cargarán automáticamente desde la API

### Operaciones CRUD con API

#### Crear Usuario
1. Presionar botón **+** (FAB)
2. Completar formulario
3. Presionar **Guardar**
4. El usuario se crea en la API
5. Feedback visual con Snackbar

#### Editar Usuario
1. Presionar ícono **lápiz** en la tarjeta del usuario
2. Modificar datos
3. Presionar **Actualizar**
4. Cambios se envían a la API

#### Eliminar Usuario
1. Presionar ícono **papelera** en la tarjeta
2. El usuario se elimina de la API
3. Se actualiza la lista automáticamente

#### Recargar Datos
- Presionar botón **Refresh** en el TopBar (solo en modo API)
- Obtiene la lista actualizada del servidor

---

## 🔐 Permisos Requeridos

### AndroidManifest.xml
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

---

## 🧪 Testing de API

### Logs de Red
El `HttpLoggingInterceptor` está configurado en nivel `BODY`, lo que permite ver en Logcat:
- URL completa de cada petición
- Headers HTTP
- Body de request y response
- Códigos de estado
- Tiempos de respuesta

### Ver Logs en Logcat
```
Filtro: "OkHttp"
```

---

## 🚀 Cambiar a tu Propia API

### Paso 1: Actualizar BASE_URL
```kotlin
// RetrofitClient.kt
private const val BASE_URL = "https://tu-api.com/api/"
```

### Paso 2: Ajustar DTOs
Modifica `UsuarioDto.kt` según el schema de tu API:
```kotlin
data class UsuarioDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nombre")  // Ajustar nombres según tu API
    val nombre: String,
    // ... otros campos
)
```

### Paso 3: Actualizar Endpoints
```kotlin
// UsuarioApiService.kt
@GET("api/usuarios")  // Tu endpoint
suspend fun obtenerUsuarios(): Response<List<UsuarioDto>>
```

### Paso 4: Autenticación (si es necesario)
Agregar interceptor de autenticación:
```kotlin
class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}
```

---

## 📊 Manejo de Errores

### Tipos de Errores Manejados
- **Error de red**: Sin conexión a internet
- **Timeout**: Servidor no responde
- **HTTP 4xx**: Error del cliente (datos inválidos)
- **HTTP 5xx**: Error del servidor
- **Parsing error**: JSON inválido

### Visualización de Errores
- Pantalla de error con mensaje descriptivo
- Botón "Reintentar"
- Snackbar para errores de operaciones

---

## 🎨 Estados de UI

### Loading State
- CircularProgressIndicator
- Campos deshabilitados
- Texto "Cargando usuarios..."

### Success State
- Lista de usuarios actualizada
- Snackbar de confirmación
- Navegación automática

### Error State
- Mensaje de error
- Botón reintentar
- Color de error theme-aware

---

## 💡 Mejoras Futuras

- [ ] Implementar paginación
- [ ] Caché con Room Database
- [ ] Refresh token automático
- [ ] Manejo offline con sincronización
- [ ] Tests unitarios y de integración
- [ ] Búsqueda y filtrado de usuarios
- [ ] Retry automático con exponential backoff
- [ ] Múltiples endpoints (usuarios, posts, etc.)

---

## 📚 Recursos

- [Retrofit Documentation](https://square.github.io/retrofit/)
- [JSONPlaceholder](https://jsonplaceholder.typicode.com/)
- [Kotlin Coroutines Guide](https://kotlinlang.org/docs/coroutines-guide.html)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)

---

## 🤝 Contribuciones

Para contribuir:
1. Fork el proyecto
2. Crea una rama para tu feature
3. Commit tus cambios
4. Push a la rama
5. Abre un Pull Request

---

## 📄 Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.
