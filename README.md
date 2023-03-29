**Function**:
```
fn doSomething(arg: Number) -> String {
    let num = "number: " + arg + " ."
    num
}
```

**Model**:
```
class Holder(
    arg1: Number,
    arg2: String
)
```

**Loop**
```
fn doSomething(arg: Number) {
    for i in 1..3 {
        println("Hello, {}", i)
    }
    
    let i = 0
    loop i != 3 {
        i++
    }
}
```

**Case**
```
fn doSomething(arg: Number) -> String {
    if arg == 1 {
        return "1"
    } else {
        return "2"
    }
}
```