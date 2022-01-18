import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.foundation.ScrollbarStyle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.adwi.shoppe.ui.navigation.ShoppeNavHost
import com.adwi.shoppe.util.Constants.APP_NAME
import com.apollographql.apollo3.annotations.ApolloExperimental
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.essenty.backpressed.BackPressedDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Stable
val LightBlue = Color(0xFFa7abcc)
val DarkerLightBlue = Color(0xFFa59fcc)
val Blue = Color(0xFF615bcc)
val DarkerBlue = Color(0xFF4c43cc)

@ExperimentalDecomposeApi
@ExperimentalCoroutinesApi
@ApolloExperimental
fun main() {
//    overrideSchedulers(main = Dispatchers.Main::asScheduler)

//    val koin = initKoin().koin
//    val lifecycle = LifecycleRegistry()
//    val root = RootComponent(
//        componentContext = DefaultComponentContext(lifecycle = lifecycle),
//        koin = koin
//    )

    application {
        val windowState = rememberWindowState()
//        LifecycleController(lifecycle, windowState)

//    var peopleState by remember { mutableStateOf(emptyList<Assignment>()) }
//    var selectedPerson by remember { mutableStateOf<Assignment?>(null) }
//
//    val peopleInSpaceApi = koin.get<PeopleInSpaceApi>()

//    LaunchedEffect(true) {
//        peopleState = peopleInSpaceApi.fetchPeople().people
//        selectedPerson = peopleState.first()
//    }

        Window(
            onCloseRequest = ::exitApplication,
            state = windowState,
            title = APP_NAME
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {
                MaterialTheme {
                    CompositionLocalProvider(
                        LocalBackPressedDispatcher provides BackPressedDispatcher(),
                        LocalScrollbarStyle provides ScrollbarStyle(
                            minimalHeight = 16.dp,
                            thickness = 8.dp,
                            shape = MaterialTheme.shapes.small,
                            hoverDurationMillis = 300,
                            unhoverColor = MaterialTheme.colors.onSurface.copy(alpha = 0.12f),
                            hoverColor = MaterialTheme.colors.onSurface.copy(alpha = 0.50f)
                        )
                    ) {
                        ShoppeNavHost()
                    }
                }
            }
//            Row(Modifier.fillMaxSize()) {
//
//                Box(Modifier.width(250.dp).fillMaxHeight().background(color = Color.LightGray)) {
////                PersonList(peopleState, selectedPerson) {
////                    selectedPerson = it
////                }
//                }
//
//                Spacer(modifier = Modifier.width(1.dp).fillMaxHeight())
//
//                Box(Modifier.fillMaxHeight()) {
////                selectedPerson?.let {
////                    PersonDetailsView(it)
////                }
//                }
//            }
        }
    }
}

//@Composable
//fun PersonList(
//    people: List<Assignment>,
//    selectedPerson: Assignment?,
//    personSelected: (person: Assignment) -> Unit
//) {
//
//    // workaround for compose desktop but if LazyColumn is empty
//    if (people.isNotEmpty()) {
//        LazyColumn {
//            items(people) { person ->
//                PersonView(person, selectedPerson, personSelected)
//            }
//        }
//    }
//}
//
//@Composable
//fun PersonView(
//    person: Assignment,
//    selectedPerson: Assignment?,
//    personSelected: (person: Assignment) -> Unit
//) {
//    Row(
//        modifier = Modifier.fillMaxWidth().clickable(onClick = { personSelected(person) })
//            .padding(8.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//
//        Column {
//            Text(
//                person.name,
//                style = if (person.name == selectedPerson?.name) MaterialTheme.typography.h6 else MaterialTheme.typography.body1
//            )
//
//            Text(text = person.craft, style = TextStyle(color = Color.DarkGray, fontSize = 14.sp))
//        }
//    }
//}
//
//@Composable
//fun PersonDetailsView(person: Assignment) {
//    LazyColumn(
//        modifier = Modifier.padding(16.dp).fillMaxWidth(),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        item(person) {
//
//            Text(person.name, style = MaterialTheme.typography.h4)
//            Spacer(modifier = Modifier.size(12.dp))
//
//            val imageUrl = person.personImageUrl
//            imageUrl?.let {
//                val imageAsset = fetchImage(it)
//                imageAsset?.let {
//                    Image(
//                        it,
//                        contentDescription = "personName",
//                        modifier = Modifier.size(240.dp)
//                    )
//                }
//            }
//            Spacer(modifier = Modifier.size(24.dp))
//
//            val bio = person.personBio ?: ""
//            Text(bio, style = MaterialTheme.typography.body1)
//        }
//    }
//}
//
//@Composable
//fun fetchImage(url: String): ImageBitmap? {
//    var image by remember(url) { mutableStateOf<ImageBitmap?>(null) }
//
//    LaunchedEffect(url) {
//        loadFullImage(url)?.let {
//            image =  makeFromEncoded(toByteArray(it)).asImageBitmap()
//        }
//    }
//
//    return image
//}
//
//fun toByteArray(bitmap: BufferedImage): ByteArray {
//    val baos = ByteArrayOutputStream()
//    ImageIO.write(bitmap, "png", baos)
//    return baos.toByteArray()
//}
//
//suspend fun loadFullImage(source: String): BufferedImage? = withContext(Dispatchers.IO) {
//    runCatching {
//        val url = URL(source)
//        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
//        connection.connectTimeout = 5000
//        connection.connect()
//
//        val input: InputStream = connection.inputStream
//        val bitmap: BufferedImage? = ImageIO.read(input)
//        bitmap
//    }.getOrNull()
//}

