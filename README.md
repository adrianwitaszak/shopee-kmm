# \[ 🚧 Work in progress 👷‍♀️⛏👷🔧️👷🔧 🚧 \]

![visitors](https://visitor-badge.laobi.icu/badge?page_id=shoppe)
![](https://img.shields.io/github/stars/adrianwitaszak/shopee-kmm)
![](https://img.shields.io/github/forks/adrianwitaszak/shopee-kmm)
![](https://img.shields.io/github/watchers/adrianwitaszak/shopee-kmm)
![](https://img.shields.io/github/commit-activity/m/adrianwitaszak/shopee-kmm)
![](https://img.shields.io/github/last-commit/adrianwitaszak/shopee-kmm)
![](https://img.shields.io/github/repo-size/adrianwitaszak/shopee-kmm)
![](https://img.shields.io/tokei/lines/github/adrianwitaszak/shopee-kmm)
![](https://img.shields.io/github/languages/count/adrianwitaszak/shopee-kmm)
![](https://img.shields.io/github/languages/top/adrianwitaszak/shopee-kmm)

# Shoppe - Client - Kotlin Multiplatform App

Shoppe is a Kotlin Multiplatform eCommerce app, that will work on 4 platforms: Android, Windows, Linux and MacOS. Shoppe
will
have 2 apps. One for sellers (this one - currently in development) and one for customers(not started yet). I have built
my own
[backend](https://github.com/adrianwitaszak/shoppe-backend) for it.

Client App uses MVI design pattern which is getting very popular right now.
In Kotlin Multiplatform main goal is to share as much as possible between platforms.
The App achievement is that it shares about 95% of code between platforms. All business logic and nearly all UI layer
is shared. App is reactive, and it uses concurrency(Coroutines) to hold states.

✨ Features
---

- [Kotlin Multiplatform](https://kotlinlang.org/lp/mobile/)
- [MVI design pattern](https://abhiappmobiledeveloper.medium.com/android-mvi-reactive-architecture-pattern-74e5f1300a87)
- [Modularity](https://proandroiddev.com/modularization-of-android-applications-in-2021-a79a590d5e5b) - feature layered
- Modern architecture
- No ViewModel - MVI handles state
- Shared business logic
- Shared UI

##<img src="https://img.icons8.com/ios/50/000000/stack.png" width="40"/>  Stack

- [Kotlin](https://kotlinlang.org/)
- [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- [Serialization](https://kotlinlang.org/docs/serialization.html)
- [Jetbrains Jetpack Compose](https://www.jetbrains.com/lp/compose-mpp/)
- [Ktor](https://ktor.io/)
- [Apollo](https://www.apollographql.com/docs/kotlin/)
- [SqlDelight](https://github.com/cashapp/sqldelight)
- [ArkIvanov's Decompose](https://arkivanov.github.io/Decompose/)
- [Kodein](https://github.com/Kodein-Framework/Kodein-DI)

📦 Install
---

 <img width="30" align="center" src="https://slackmojis.com/emojis/28070-gradle-elephant/download"> Add it in your root build.gradle at the end of repositories:

 <img width="30" align="center" src="https://slackmojis.com/emojis/4696-android_studio/download"> Android Studio

1. `Android Studio` -> `File` -> `New` -> `From Version control` -> `Git`
2. Enter `https://github.com/adrianwitaszak/neumorph-ui.git` into URL field an press `Clone` button

 <img width="30" align="center" src="https://slackmojis.com/emojis/1263-terminal/download"> Command-line + Android Studio

1. Run `git clone https://github.com:adrianwitaszak/neumorph-ui.git` command to clone project
2. Open `Android Studio` and select `File | Open...` from the menu. Select cloned directory and press `Open` button

This application supports
the [Getting Started with Java on Heroku](https://devcenter.heroku.com/articles/getting-started-with-java) article -
check it out.

[![Deploy to Heroku](https://www.herokucdn.com/deploy/button.png)](https://heroku.com/deploy)

## Running Locally

Make sure you have Java and Maven installed. Also, install the [Heroku Toolbelt](https://toolbelt.heroku.com/).

```sh
$ git clone https://github.com/orangy/ktor-heroku-start.git
$ cd ktor-heroku-start
$ gradlew install
$ heroku local:start
```

Your app should now be running on [localhost:8080](http://localhost:8080/).

If you're going to use a database, ensure you have a local `.env` file that reads something like this:

```
MONGO_URI=Your_Mongo_Database_Access_Key
PORT=YOUR_PORT

```

## Deploying to Heroku

```sh
$ heroku create
$ git push heroku master
$ heroku open
```

## 🤝 Contributing [![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)

Read our [contributing guide](doc/CONTRIBUTING.md) and let's build a better antd together.

We welcome all contributions. Please read our [CONTRIBUTING.md](doc/CONTRIBUTING.md) first. You can submit any ideas
as [pull requests](https://github.com/adrianwitaszak/neumorph-ui/pulls) or
as [GitHub issues](https://github.com/adrianwitaszak/neumorph-ui/issues). If you'd like to improve code, check out the
Development Instructions and have a good time! :)

## ⭐ Author

[<img width="20" src="https://emojis.slackmojis.com/emojis/images/1643514782/7926/twitter.png?1643514782">](https://twitter.com/adrianwita)
@adrianwita

[<img width="20" src="https://emojis.slackmojis.com/emojis/images/1643514111/711/linkedin.png?1643514111">](https://www.linkedin.com/in/adrian-witaszak)
@adrian-witaszak

[<img width="400" src="https://helloimjessa.files.wordpress.com/2021/06/bmc-button.png">](https://www.buymeacoffee.com/adrianwitay)

## <img width="40" src="https://emojis.slackmojis.com/emojis/images/1643517461/34922/read-the-rules.gif?1643517461"> License

```
MIT License

Copyright (c) 2022 Adrian Witaszak

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

