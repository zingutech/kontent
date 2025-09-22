package tech.zingu.kontent.pages

import androidx.compose.runtime.Composable
import kotlinx.browser.document
import org.jetbrains.compose.web.attributes.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.Node
import org.w3c.dom.events.MouseEvent
import tech.zingu.kontent.extension.*

@Composable
fun PageHome() {
    Div {
        SideMenu()
        Div(attrs = { classes("lg:pl-72") }) {
            TopBar()
            Content()
        }
    }
}

fun handleWindowClickOnHomePage(event: MouseEvent) {
    val elementTarget = event.target as? Node
    if (elementTarget != null && document.getElementById("user-menu-button")?.contains(elementTarget) == false) {
        setUserDropdownMenuVisibility(isVisible = false)
    }
    if (elementTarget != null && document.getElementById("mobile-menu-backdrop")?.contains(elementTarget) == true) {
        setMobileMenuVisibility(isVisible = false)
    }
}

@Composable
private fun SideMenu() {
    // Off-canvas menu for mobile, show/hide based on off-canvas menu state.
    Div(attrs = {
        id("mobile-menu")
        classes("relative", "z-50", "lg:hidden", "pointer-events-none")
        role("dialog")
        ariaModal("true")
    }) {
        Div(attrs = {
            id("mobile-menu-backdrop")
            classes("fixed", "inset-0", "bg-gray-900/80", "opacity-0")
        })
        Div(attrs = { classes("fixed", "inset-0", "flex") }) {
            Div(attrs = {
                id("mobile-menu-body")
                classes("relative", "mr-16", "flex", "w-full", "max-w-xs", "flex-1", "-translate-x-full")
            }) {
                Div(attrs = {
                    id("mobile-menu-close")
                    classes("absolute", "left-full", "top-0", "flex", "w-16", "justify-center", "pt-5", "opacity-0")
                }) {
                    Button(attrs = {
                        type(ButtonType.Button)
                        classes("-m-2.5", "p-2.5")
                        onClick { setMobileMenuVisibility(isVisible = false) }
                    }) {
                        Span(attrs = { classes("sr-only") }) { Text("Close sidebar") }
                        CloseIconSvg()
                    }
                }
                NavBar(isMobile = true)
            }
        }
    }
    // Static sidebar for desktop
    Div(attrs = { classes("hidden", "lg:fixed", "lg:inset-y-0", "lg:z-50", "lg:flex", "lg:w-72", "lg:flex-col") }) {
        NavBar(isMobile = false)
    }
}

@Composable
private fun TopBar() {
    Div(attrs = { classes("sticky", "top-0", "z-40", "flex", "h-16", "shrink-0", "items-center", "gap-x-4", "border-b", "border-gray-200", "bg-white", "px-4", "shadow-sm", "sm:gap-x-6", "sm:px-6", "lg:px-8") }) {
        Button(attrs = {
            type(ButtonType.Button)
            classes("-m-2.5", "p-2.5", "text-gray-700", "lg:hidden")
            onClick { setMobileMenuVisibility(isVisible = true) }
        }) {
            Span(attrs = { classes("sr-only") }) { Text("Open sidebar") }
            ListMenuIconSvg()
        }

        // separator
        Div(attrs = {
            classes("h-6", "w-px", "bg-gray-900/10", "lg:hidden")
            ariaHidden("true")
        })

        Div(attrs = { classes("flex", "flex-1", "gap-x-4", "self-stretch", "lg:gap-x-6") }) {
            Form(action = "#", attrs = { method(FormMethod.Get); classes("relative", "flex", "flex-1") }) {
                Label(forId = "search-field", attrs = { classes("sr-only") }) {
                    Text("Search")
                }
                MagnifierIconSvg()
                Input(type = InputType.Search,
                      attrs = {
                          id("search-field")
                          name("search")
                          classes("block", "h-full", "w-full", "border-0", "py-0", "pl-8", "pr-0", "text-gray-900", "placeholder:text-gray-400", "focus:ring-0", "sm:text-sm")
                          placeholder("Search...")
                      })
            }

            Div(attrs = { classes("flex", "items-center", "gap-x-4", "lg:gap-x-6") }) {
                Button(attrs = {
                    type(ButtonType.Button)
                    classes("-m-2.5", "p-2.5", "text-gray-400", "hover:text-gray-500")
                }) {
                    Span(attrs = { classes("sr-only") }) { Text("View notifications") }
                    BellIconSvg()
                }

                // separator
                Div(attrs = {
                    classes("hidden", "lg:block", "lg:h-6", "lg:w-px", "lg:bg-gray-900/10")
                    ariaHidden("true")
                })

                Div(attrs = { classes("relative") }) {
                    Button(attrs = {
                        id("user-menu-button")
                        type(ButtonType.Button)
                        classes("-m-1.5", "flex", "items-center", "p-1.5")
                        ariaExpanded("false")
                        ariaHasPopup("true")
                        onClick { setUserDropdownMenuVisibility(isVisible = true) }
                    }) {
                        Span(attrs = { classes("sr-only") }) { Text("Open user menu") }
                        Img(attrs = { classes("h-8", "w-8", "rounded-full", "bg-gray-50") },
                            src = "https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80")
                        Span(attrs = { classes("hidden", "lg:flex", "lg:items-center") }) {
                            Span(attrs = {
                                classes("ml-4", "text-sm", "font-semibold", "leading-6", "text-gray-900")
                                ariaHidden("true")
                            }) {
                                Text("Tom Cook")
                            }
                        }
                        ArrowDownIconSvg()
                    }
                    Div(attrs = {
                        id("user-menu")
                        classes("absolute", "right-0", "z-10", "mt-2.5", "w-32", "origin-top-right", "rounded-md", "bg-white", "py-2", "shadow-lg", "ring-1", "ring-gray-900/5", "focus:outline-none", "transition", "ease-in", "duration-75", "transform", "opacity-0", "scale-95")
                        role("menu")
                        ariaOrientation("vertical")
                        ariaLabelledBy("user-menu-button")
                        tabIndex(-1)
                    }) {
                        UserMenuItem(menuId = "user-menu-item-0", title = "Your profile")
                        UserMenuItem(menuId = "user-menu-item-1", title = "Sign out")
                    }
                }
            }
        }
    }
}

@Composable
private fun Content() {
    Main(attrs = { classes("py-10") }) {
        Div(attrs = { classes("px-4", "sm:px-6", "lg:px-8") }) {
            // section content
        }
    }
}

@Composable
private fun NavBar(isMobile: Boolean) {
    val classes = listOf("flex", "grow", "flex-col", "gap-y-5", "overflow-y-auto", "bg-gray-900", "px-6", "pb-4") +
                  if (isMobile) listOf("ring-1", "ring-white/10") else emptyList()
    Div(attrs = { classes(classes) }) {
        Div(attrs = { classes("flex", "h-16", "shrink-0", "items-center") }) {
            Img(alt = "Kontent",
                attrs = { classes("h-8", "w-auto") },
                src = "https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=500")
        }
        Nav(attrs = { classes("flex", "flex-1", "flex-col") }) {
            Ul(attrs = {
                classes("flex", "flex-1", "flex-col", "gap-y-7")
                role("list")
            }) {
                Li {
                    Ul(attrs = {
                        classes("-mx-2", "space-y-1")
                        role("list")
                    }) {
                        LiMenuItem(icon = { HomeIconSvg() }, title = "Dashboard", isCurrent = true)
                        LiMenuItem(icon = { TeamIconSvg() }, title = "Team")
                        LiMenuItem(icon = { FolderIconSvg() }, title = "Projects")
                        LiMenuItem(icon = { CalendarIconSvg() }, title = "Calendar")
                        LiMenuItem(icon = { DocumentsIconSvg() }, title = "Documents")
                        LiMenuItem(icon = { PieChartIconSvg() }, title = "Reports")
                    }
                }
                Li {
                    Div(attrs = { classes("text-xs", "font-semibold", "leading-6", "text-gray-400") }) {
                        Text("Your teams")
                    }
                    Ul(attrs = {
                        classes("-mx-2", "mt-2", "space-y-1")
                        role("list")
                    }) {
                        LiTeamMember("Heroicons")
                        LiTeamMember("Tailwind Labs")
                        LiTeamMember("Workcation")
                    }
                }
                Li(attrs = { classes("mt-auto") }) {
                    A(href = "#",
                      attrs = { classes("group", "-mx-2", "flex", "gap-x-3", "rounded-md", "p-2", "text-sm", "font-semibold", "leading-6", "text-gray-400", "hover:bg-gray-800", "hover:text-white") }
                    ) {
                        GearIconSvg()
                        Text(" Settings ")
                    }
                }
            }
        }
    }
}

@Composable
private fun LiMenuItem(
    icon: @Composable () -> Unit,
    title: String,
    isCurrent: Boolean = false
) {
    Li {
        val selectStateClasses = if (isCurrent) {
            listOf("bg-gray-800", "text-white")
        } else {
            listOf("text-gray-400", "hover:text-white", "hover:bg-gray-800")
        }
    
        A(href = "#",
          attrs = { classes(selectStateClasses + listOf("group", "flex", "gap-x-3", "rounded-md", "p-2", "text-sm", "leading-6", "font-semibold")) }
        ) {
            icon()
            Text(title)
        }
    }
}

@Composable
private fun LiTeamMember(
    name: String,
    isCurrent: Boolean = false
) {
    Li {
        val selectStateClasses = if (isCurrent) {
            listOf("bg-gray-800", "text-white")
        } else {
            listOf("text-gray-400", "hover:text-white", "hover:bg-gray-800")
        }
    
        A(href = "#",
          attrs = { classes(selectStateClasses + listOf("group", "flex", "gap-x-3", "rounded-md", "p-2", "text-sm", "leading-6", "font-semibold")) }
        ) {
            Span(attrs = { classes("flex", "h-6", "w-6", "shrink-0", "items-center", "justify-center", "rounded-lg", "border", "border-gray-700", "bg-gray-800", "text-[0.625rem]", "font-medium", "text-gray-400", "group-hover:text-white") }) {
                Text("${name.first()}")
            }
            Span(attrs = { classes("truncate") }) { Text(name) }
        }
    }
}

@Composable
private fun UserMenuItem(
    menuId: String,
    title: String
) {
    A(href = "#",
      attrs = {
          id(menuId)
          classes("block", "px-3", "py-1", "text-sm", "leading-6", "text-gray-900", "hover:bg-gray-50")
          role("menuitem")
          tabIndex(-1)
          onClick { setUserDropdownMenuVisibility(isVisible = false) }
      }
    ) {
        Text(title)
    }
}

private fun setMobileMenuVisibility(isVisible: Boolean) {
//    document.getElementById("mobile-menu")?.run {
//        if (isVisible) classList.replace("pointer-events-none", "pointer-events-auto")
//        else classList.replace("pointer-events-auto", "pointer-events-none")
//    }
    document.getElementById("mobile-menu-backdrop")?.run {
        // Entering: "transition-opacity ease-linear duration-300"
        // from: "opacity-0" to: "opacity-100"
        // Leaving: "transition-opacity ease-linear duration-300"
        // from: "opacity-100" to: "opacity-0"
        classList.addIfNotExist("transition-opacity", "ease-linear", "duration-300")
        if (isVisible) classList.replaceOrAddIfNotExist(
            "opacity-0" to "opacity-100",
            "pointer-events-none" to "pointer-events-auto"
        )
        else classList.replaceOrAddIfNotExist(
            "opacity-100" to "opacity-0",
            "pointer-events-auto" to "pointer-events-none"
        )
    }
    document.getElementById("mobile-menu-body")?.run {
        // Entering: "transition ease-in-out duration-300 transform"
        // from: "-translate-x-full" to: "translate-x-0"
        // Leaving: "transition ease-in-out duration-300 transform"
        // from: "translate-x-0" to: "-translate-x-full"
        classList.addIfNotExist("transition", "ease-in-out", "duration-300", "transform")
        if (isVisible) classList.replaceOrAddIfNotExist(
            "-translate-x-full" to "translate-x-0",
            "pointer-events-none" to "pointer-events-auto"
        )
        else classList.replaceOrAddIfNotExist(
            "translate-x-0" to "-translate-x-full",
            "pointer-events-auto" to "pointer-events-none"
        )
    }
    document.getElementById("mobile-menu-close")?.run {
        // Entering: "ease-in-out duration-300"
        // from: "opacity-0" to: "opacity-100"
        // Leaving: "ease-in-out duration-300"
        // From: "opacity-100" to: "opacity-0"
        classList.addIfNotExist("ease-in-out")
        if (isVisible) classList.replaceOrAddIfNotExist("opacity-0" to "opacity-100")
        else classList.replaceOrAddIfNotExist("opacity-100" to "opacity-0")
    }
}

private fun setUserDropdownMenuVisibility(isVisible: Boolean) {
    document.getElementById("user-menu")?.run {
        // Entering: "transition ease-out duration-100"
        // from: "transform opacity-0 scale-95" to: "transform opacity-100 scale-100"
        // Leaving: "transition ease-in duration-75"
        // from: "transform opacity-100 scale-100" to: "transform opacity-0 scale-95"
        classList.addIfNotExist("transition")
        if (isVisible) classList.replaceOrAddIfNotExist(
            "ease-in" to "ease-out",
            "duration-75" to "duration-100",
            "transform" to "transform",
            "opacity-0" to "opacity-100",
            "scale-95" to "scale-100"
        )
        else classList.replaceOrAddIfNotExist(
            "ease-out" to "ease-in",
            "duration-100" to "duration-75",
            "transform" to "transform",
            "opacity-100" to "opacity-0",
            "scale-100" to "scale-95"
        )
    }
}
