### Feedback

Buen trabajo! Comprendiste gran parte de los conceptos explicados en el bootcamp. Encontré algunos detalles que podrías mejorar:

##### Schedulers and dispatchers:
- Olvidaste colocar el IO dispatcher para los métodos insertUserHangman y getTopTenScore en data. Usa withContext(dispatcher) para hacer el cambio en el repository o dataSource.
- Evita colocar directamente los schedulers/dispatchers como en GameViewModel. Es mejor práctica inyectarlos desde el constructor, además así se facilita la parte de testing.

##### En los ViewModel:
- Crea siempre los MutableStateFlows como val, la instancia no se debe poder reasignar. Encontré este error en el LoginViewModel, y un ejemplo de como lo tienes correcto es en el ScoreViewModel.
- Adicional, no expongas MutableStateFlow como public, ya que alguien podría cambiar su valor desde fuera del ViewModel. Debes exponer siempre tipos immutable, por ejemplo para el caso de MutableStateFlow debes exponerla como tipo StateFlow. Debes tener algo como esto:

```kotlin
private val _wordToGame: MutableStateFlow<String> = MutableStateFlow<String>("")
val wordToGame: StateFlow<String> = _wordToGame.asStateFlow()
```

##### Testing
- Te faltó crear tests para la parte del ViewModel. Recuerda que es importante conocer como intercambiar los dispatchers y schedulers por su versión para testing.

##### GameFragment (líneas 66-70):
- Esta parte no requiere que corra en una coroutine. El método como lo tienes en el ViewModel ya tiene la implementación con RxJava, es seguro llamar gameViewModel.getRandomWords desde el main thread en el fragment, además que no estás esperando ningún valor de este método.
- La parte importante que debe correr en coroutines es al momento de observar los flows/liveData que tengas en el viewModel.
```kotlin
lifecycleScope.launch {
    repeatOnLifecycle(Lifecycle.State.STARTED){
        gameViewModel.getRandomWords()
    }
}
```

Por último, como sugerencia adicional, te recomiendo evitar en lo posible tener código comentado y código que no estés usando.
