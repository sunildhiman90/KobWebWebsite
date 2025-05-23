package com.codingambitions.kobwebfirstwebsite.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.browser.dom.ElementTarget
import com.varabyte.kobweb.compose.css.AlignSelf
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.JustifyItems
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.forms.Input
import com.varabyte.kobweb.silk.components.forms.Label
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.attributes.ButtonType
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Form
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Text


//Route creation
// AboutUs -> about-us

val FormStyle = CssStyle {
    base {
        Modifier.border(3.px, LineStyle.Solid, Color.lightgray)
    }
}

val ButtonStyle = CssStyle {
    base {
        Modifier.backgroundColor(Color.blue)
            .color(Color.white)
            .padding(14.px, 14.px)
            .margin(8.px, 0.px)
            .cursor(Cursor.Pointer)
            .fillMaxWidth()
    }
}

val H2Style = CssStyle {
    base {
        Modifier.textAlign(TextAlign.Center)
    }
}


val AvatarStyle = CssStyle {
    base {
        Modifier
            .width(40.percent)
            .borderRadius(50.percent)
            .alignSelf(AlignSelf.Center)
    }
}

val InputStyle = CssStyle {
    base {
        Modifier
            .width(100.percent)
            .padding(top = 12.px, bottom = 12.px, left = 24.px, right = 24.px)
            .display(DisplayStyle.InlineBlock)
            .margin(top = 16.px, bottom = 16.px)
            .border(width = 1.px, style = LineStyle.Solid, color = Color.gray)
    }
}

val MainContainerStyle = CssStyle {
    base {
        Modifier
            .fontFamily("Arial", "Helvetica")
            .display(DisplayStyle.Flex)
            .justifyContent(JustifyContent.Center)
            .alignContent(AlignContent.Center)
            .alignItems(AlignItems.Center)
    }
}

val TopContainerStyle = CssStyle {
    base {
        Modifier
            .fillMaxWidth()
            .justifyItems(JustifyItems.Center)
    }
}

@Page
@Composable
fun Login() {

    val scope = rememberCoroutineScope()
    val context = rememberPageContext()
    var isAuthenticated by remember { mutableStateOf(false) }

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(isAuthenticated) {
        if (isAuthenticated) context.router.navigateTo("dashboard")
    }

    Column(modifier = TopContainerStyle.toModifier().alignItems(AlignItems.Center)) {
        H2(attrs = H2Style.toAttrs()) {
            Text("Login")
        }
        Box(modifier = MainContainerStyle.toModifier().fillMaxWidth()) {
            Form(attrs = FormStyle.toAttrs()) {
                Column(
                    modifier = Modifier.fillMaxWidth().alignItems(AlignItems.Stretch),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image("profile.png", modifier = AvatarStyle.toModifier().padding(top = 16.px))

                    Column(
                        modifier = Modifier.padding(16.px).fillMaxWidth().alignItems(AlignItems.Stretch),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Column {
                            Label(target = ElementTarget.NextSibling, label = "Username")
                            Input(
                                value = username,
                                modifier = InputStyle.toModifier(),
                                type = InputType.Text,
                                placeholder = "Enter Username",
                                onValueChange = {
                                    username = it
                                })
                        }

                        Column {
                            Label(target = ElementTarget.NextSibling, label = "Password")
                            Input(
                                modifier = InputStyle.toModifier(),
                                value = password,
                                type = InputType.Password,
                                placeholder = "Enter Password",
                                onValueChange = {
                                    password = it
                                })
                        }

                        com.varabyte.kobweb.silk.components.forms.Button(
                            modifier = ButtonStyle.toModifier(),
                            type = ButtonType.Button,
                            onClick = {
                                scope.launch {
                                    if (username.isEmpty() || password.isEmpty()) {
                                        //just set errors
                                    } else {

                                        //call api, and use response after waiting
                                        delay(1000)
                                        isAuthenticated = true
                                    }
                                }
                            }
                        ) {
                            Text("Submit")
                        }

                        Row {
                            SpanText("Dont have an account?")
                            Link("/signup") {
                                Text("Signup")
                            }
                        }
                    }
                }
            }
        }
    }
}
