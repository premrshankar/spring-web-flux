---
layout: default
title: Coding Exercises
parent: Using Other Reactive Operators
nav_order: 8
---

# Exercises


## Exercise 1
You are given a list of stock symbols, and you need to fetch their historical price data from an external API. You want to keep the order of the received data. Using the [concatMap] method, implement a solution that fetches the historical price data for each stock symbol in a sequential and non-interleaved manner.

1. Given the following code:
    ```java
    import reactor.core.publisher.Flux;
    import reactor.core.publisher.Mono;

    import java.time.Duration;
    import java.util.Arrays;
    import java.util.List;

    public class Exercise01 {

        public static void main(String[] args) throws InterruptedException {
            List<String> stockSymbols = Arrays.asList("AAPL", "GOOG", "MSFT", "AMZN", "FB");

            // TODO: Create a Flux from stockSymbols
            // TODO: Fetch historical prices
            // TODO: Subscribe to the Flux

            Thread.sleep(5500);
        }

        // Simulates an external API call to get historical stock prices
        public static Mono<List<Double>> fetchHistoricalPrices(String stockSymbol) {
            int c = stockSymbol.charAt(0);
            return Mono.just(Arrays.asList(c*10.0, c*20.0, c*30.0))
                    .delayElement(Duration.ofMillis(1000));
        }
    }
    ```
2. Create a `Flux` from the `stockSymbols` list.
3. Using the method `fetchHistoricalPrices` and the `concatMap` operator, fetch the historical prices for each stock.
4. Subscribe to the `Flux`, printing the emitted items.
6. Run the `Exercise01` class and analyze the output.

----

## Exercise 2
You are given a `Flux` of stock symbols that emits a new symbol every second. Your task is to fetch the latest stock price for each emitted symbol using an external API. However, you should only fetch the latest stock price for the most recent symbol emitted by the `Flux`. If a new symbol is emitted before the previous API call is completed, cancel the previous call and fetch the stock price for the new symbol. Implement a solution using the [switchMap]() operator.
1. Given the following code:
    ```java
    import reactor.core.publisher.Flux;
    import reactor.core.publisher.Mono;

    import java.time.Duration;

    public class Exercise02 {
        public static void main(String[] args) throws InterruptedException {
            Flux<String> stockSymbols = Flux.just("AAPL", "GOOG", "MSFT", "AMZN", "FB")
                    .delayElements(Duration.ofSeconds(1));

            // TODO: If a new symbol is emitted before the previous API call is completed,
            // use switchMap to cancel the previous call and switch to the new one.
            // TODO: Subscribe to Flux to print the latest stock price

            Thread.sleep(15000);
        }

        // Simulates an external API call to get the latest stock price,
        // sometimes taking less than 1 second, other times taking more
        public static Mono<Double> fetchLatestPrice(String stockSymbol) {
            int c = stockSymbol.charAt(0);
            return Mono.just(c*10.0)
                    .delayElement(Duration.ofMillis(935+c));
        }
    }
   ```
2. Using the method `fetchLatestPrice` and the `switchMap` operator, fetch the latest price for each stock.
3. Subscribe to the `Flux`, printing the emitted items.
4. Run the `Exercise02` class and analyze the output. Are the prices of all stocks printed? Why or why not?

----

## Exercise 3
You are given two publishers, one that emits a list of stock symbols and another that emits their corresponding market names. Your task is to combine the elements from both publishers such that each stock symbol is paired with its respective market name. 
1. Given the following code:
    ```java
    public class Exercise03 {
        public static void main(String[] args) {
            Flux<String> stockSymbols = Flux.just("AAPL", "GOOG", "MSFT", "AMZN", "FB");
            Flux<String> marketNames = Flux.just("NASDAQ", "NASDAQ", "NASDAQ", "NASDAQ", "NASDAQ");

            // TODO: Combine the elements from both publishers using this format: {stock} - {market}
        }
    }
   ```
2. Combine the elements from both publishers using this format: `{STOCK} - {MARKET}`.
3. Subscribe to the `Flux`, printing the emitted items.
4. Run the `Exercise03` class and analyze the output.

----

## Exercise 4
You are given a `Flux` that emits a list of stock prices. Your task is to filter the prices and only emit the prices that are greater than a given threshold. 
1. Given the following code:
    ```java
    public class Exercise04 {
        public static void main(String[] args) {
            Flux<Double> stockPrices = Flux.just(120.0, 140.0, 130.0, 110.0, 150.0);
            double threshold = 200.0;

            // TODO: If the stock price is greater than the threshold, emit it; otherwise, ignore it
            Flux<Double> filteredStockPrices = null;

            filteredStockPrices.subscribe(
                    value -> System.out.println("Filtered value: " + value),
                    error -> System.err.println("Error: " + error.getMessage()),
                    () -> System.out.println("Filtering completed")
            );
        }
    }
   ```
2. Filter the values of `stockPrices`. If the stock price is greater than the threshold, emit it. Otherwise, ignore it.
3. Subscribe to the `Flux`, printing the emitted items.
4. Run the `Exercise04` class and analyze the output. What's printed? Why?

----

## Exercise 5
You are given a `Flux` that emits a list of stock symbols. Your task is to check if the `Flux` is empty or not. If it is empty, use an alternative `Flux` containing a default list of stock symbols.
1. Given the following code:
    ```java
    import reactor.core.publisher.Flux;

    public class Exercise05 {

        public static void main(String[] args) {
            Flux<String> stockSymbols = Flux.empty(); // Empty Flux
            Flux<String> defaultSymbols = Flux.just("AAPL", "GOOG", "MSFT", "AMZN", "FB");

            // TODO: Check if stockSymbols is empty or not. If it is empty, use defaultSymbols
        }
    }
   ```
2. Utilizing the appropriate operator, use `defaultSymbols` if `stockSymbols` is empty (which it is, but assume we don't know it).
3. Subscribe to the `Flux`, printing the emitted items.
4. Run the `Exercise05` class and analyze the output.

----

## Exercise 6
You are given a `Flux` that emits a list of stock prices. Your task is to calculate the total sum of all the prices using the appropriate operator.
1. Given the following code:
    ```java
    import reactor.core.publisher.Flux;

    public class Exercise06 {

        public static void main(String[] args) {
            Flux<Double> stockPrices = Flux.just(100.0, 200.0, 300.0, 400.0);

            // TODO: Calculate the total sum of all the prices
        }
    }
   ```
2. Utilizing the appropriate operator, calculate the total sum of all the prices.
3. Subscribe to the `Flux`, printing the emitted item (the total).
4. Run the `Exercise06` class and analyze the output.

----

## Exercise 7
You are given a `Flux` that emits a list of stock prices:
```java
import reactor.core.publisher.Flux;

public class Exercise07 {
    public static void main(String[] args) {
        Flux<Double> stockPrices = Flux.just(120.0, 140.0, 130.0, 110.0, 150.0);

        // TODO: Your implementation goes here
    }
}
```
Your task is to implement the following requirements using hooks:
1. When the `Flux` starts emitting, print `"First stock price incoming..."`
2. When the `Flux` completes, print `"All stock prices processed."`
3. Print each price in the `Flux`.
4. When the `Flux` is subscribed, print `"Subscription started."`


## Exercise 1
This exercise simulates fetching user data from a remote API. The API can return either a list of user names or an error.
1. Given the following code where the method `simulateApiCall` simulates an API call and based on a random value, either returns a list of user names or throws an exception:
    ```java
    import reactor.core.publisher.Flux;

    import java.util.Arrays;
    import java.util.List;
    import java.util.Random;

    public class Exercise01 {
        public static void main(String[] args) {
            Flux<List<String>> userNamesFlux = Flux.defer(() -> {
                // TODO Create either a Flux with the result or with an error
                return null;
            });

            userNamesFlux.subscribe(userNames -> System.out.println("User data: " + userNames),
                    error -> System.out.println("Error fetching user data: " + error.getMessage()));
        }

        public static List<String> simulateApiCall() {
            List<String> userNames = Arrays.asList("Alice", "Bob", "Carol", "David");
            Random random = new Random();
            if (random.nextBoolean()) {
                throw new RuntimeException("Remote API error");
            }
            return userNames;
        }
    }
   ```
2. Implement the body of the lambda expression passed to `Flux.defer` to call the method `simulateApiCall`:
    - Create a `Flux` that uses the `Flux.error` method to emit an error if the `simulateApiCall` method throws an exception. 
    - Otherwise, create a `Flux` with the list returned by the method.
3. Run the `Exercise01` class and analyze the output.

----

## Exercise 2
In the following exercise, you'll implement the reactive equivalent of the following imperative `try-catch-finally` block.
1. Create a class named `Exercise02` with a `main` method.
2. Given the following code:
    ```java
    List<Integer> inputList = Arrays.asList(1, 2, -3, 4);

    try {
        for (Integer num : inputList) {
            if (num < 0) {
                throw new IllegalArgumentException("Negative numbers are not allowed.");
            }
            System.out.println(num * 2);
        }
    } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
    } finally {
        System.out.println("Finished processing the list.");
    }
   ```
3. Create a `Flux` with the values of `inputList` (if you prefer, you can create a `Flux` from this list).
4. Implement the reactive equivalent of the above `try-catch-finally` block using the `map`, `doOnError`, `doFinally`, and `subscribe` operators.
5. Run the `Exercise02` class and analyze the output.

**HINTS:** 
- Use the `map` operator to double each integer and throw an `IllegalArgumentException` if a negative number is found.
- Handle the error using the `doOnError` operator, printing the appropriate message.
- Use the `doFinally` operator to print the appropriate message regardless of whether an error occurred or not. 
- Subscribe to the `Flux` to print each doubled value, also passing an empty error consumer to avoid printing the stack trace of the exception.

----

## Exercise 3
In this exercise, you are given a list of URLs, each containing a JSON string. Your task is to create a program that fetches JSON data from the URLs and parses them into strings. Some of the URLs may return invalid JSON responses, causing a exception to be thrown during the parsing process. In case of an invalid JSON string, you should fetch data from a fallback URL, parse it, and return the result.
1. Create a class named `Exercise03` with a `main` method.
2. Given the following code were the methods `fetchData` and `parseJson` simulate the fetching and parsing of JSON data, respectively:
    ```java
    import reactor.core.publisher.Flux;

    public class Exercise03 {
        public static void main(String[] args) {
            String fallbackUrl = "fallbackUrl";

            Flux.just("url1", "url2", "url3", "url4")
                    // TODO Use flatMap/map and onErrorResume to implement the functionality
                    .subscribe(System.out::println);
        }

        public static String fetchData(String url) {
            // Simulates fetching JSON data from the URL
            return "{\"data\": \"" + url + "\"}";
        }

        public static String parseJson(String jsonString) {
            // Simulates the parsing of jsonString and throwing an exception if it is invalid
            String str = "";
            if(jsonString.contains("url3"))
                throw new RuntimeException("Invalid JSON");
            else
                str = jsonString.toUpperCase();
            return str;
        }
    }
   ```
3. Fetch and parse the JSON data handling any exception using the `onErrorResume` operator. In case of an exception, fetch data from the fallback URL, parse it, and return the result.
4. Run the `Exercise03` class and analyze the output.

**Note:** This exercise can be implemented in more than one way. Probably one of the easiest is to get, inside a `flatMap` operator, the JSON string as a `Mono`, and then, use map to parse it and `onErrorResume` to use the fallback URL. Take a look at the solution to review this approach if you're having a hard time with this exercise.

----

## Exercise 4
In this exercise, you are given a list of integers. Your task is to implement the error handling of a program that processes a number using `doOnError` and `onErrorResume`.
1. Create a class named `Exercise04` with a `main` method.
2. Given the following code:
    ```java
    import reactor.core.publisher.Flux;
    import reactor.core.publisher.Mono;

    public class Exercise04 {
        public static void main(String[] args) {
            Flux.just(1, 2, 3, 4, 5)
                    .flatMap(number -> {
                        // TODO Implement reactive sequence using doOnError and onErrorResume
                        return null;
                    })
                    .subscribe(System.out::println);
        }

        public static Mono<Integer> processNumber(int number) {
            int doubled = number * 2;
            if (doubled % 4 == 0)
                return Mono.error(new IllegalArgumentException("Result is divisible by 4"));
            else
                return Mono.just(doubled);
        }
    }
   ```
3. Inside the `flatMap` operator:
  - Call the method `processNumber` to get a `Mono<Integer>`.
  - Use the `doOnError` method to print "Error processing [number]: " followed by the error message, but only for `IllegalArgumentException`.
  - Use the `onErrorResume` operator to handle any error by returning a `Mono` that emits `-1` as the fallback value.
4. Run the `Exercise04` class and analyze the output.

----

## Exercise 5
In this exercise, you'll use [Exceptions.propagate(Throwable)]() to propagate a checked exception as a `RuntimeException`.
1. Create a class named `Exercise04` with a `main` method.
2. Given the following code:
    ```java
    import reactor.core.publisher.Flux;

    public class Exercise05 {
        public static void main(String[] args) {
            Flux<String> stringFlux = Flux.just("1", "2", "three", "4", "5");

            stringFlux.map(stringNumber -> {
                        // TODO Parse each element and propagate any NumberFormatException
                        return null;
                    })
                    .subscribe(
                            System.out::println,
                            error -> System.out.println("Error: " + error.getMessage())
            );
        }
    }
   ```
3. Inside the `map` operator, parse the strings into integers.
4. If a string is not a valid number, throw a `NumberFormatException` and propagate it as a `RuntimeException` using `Exceptions.propagate(Throwable)` method.
5. Run the `Exercise05` class and analyze the output.