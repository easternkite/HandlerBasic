# HandlerBasic

핸들러는 MessageQueue의 메세지, 혹은 Runnable 객체를 보내고 처리하는 역할을 수행합니다.
각각의 핸들러 인스턴스는 싱글 스레드와 스레드의 메세지큐와 관련이 있습니다. 핸들러는 생성될 때 Looper에 바인딩됩니다. 핸들러는 루퍼의 메세지 큐로 메세지나, Runnable 객체를 전달하고 루퍼가 가지고 있는 쓰레드에서 이를 실행합니다.

핸들러의 주된 역할은 다음과 같습니다.
1. 미래에 실행될 메세지나 Runnable객체에 대한 스케쥴링 진행합니다.
2. 별도의 쓰레드에서도 사용할 수 있도록 작업을 Looper의 MessageQueue에 넣는 작업을 수행합니다.

핸들러는 post 메서드를 통해 Runnable 객체, 혹은 메세지를 처리하기 위해 메세지큐에 Enqueue 합니다. 

## Looper
쓰레드에서 처리되는 메세지들의 루프를 담당하는 클래스입니다. 쓰레드는 기본적으로 자체적인 메세지 루프를 가지고 있지 않고, prepare 메서드를 통해 메세지 루프를 생성하고 실행합니다. 그 다음, loop() 메서드를 통해 루프 실행이 종료될 때까지 메세지들은 처리합니다.

대부분은 Handler를 통해 메시지 루프와 상호작용할 수 있습니다.

다음은 루퍼 쓰레드를 사용하는 일반적인 예 입니다. 각각의 prepare, loop 메서드를 통해 핸들러를 생성하고 루퍼와 통신합니다.
```kotlin
class LooperThread : Thread() {  
    private lateinit var mHandler: Handler  
  
    override fun run() {  
        Looper.prepare()  
        mHandler = Handler(Looper.getMainLooper()) {  
            println("Message received: ${it.obj}")  
            true  
        }  
    }  
}
```

## Message Queue
Looper를 통해 전달되는 메세지들을 저장하고 있는 자료구조입니다. 메세지들은 MessageQueue로 직접적으로 추가될 수 없으며, Looper와 연결된 Handler를 통해서만 Enqueue될 수 있습니다.

Looper.myQueue()를 통해 현재 쓰레드의 MessageQueue 객체를 얻을 수 있습니다.
