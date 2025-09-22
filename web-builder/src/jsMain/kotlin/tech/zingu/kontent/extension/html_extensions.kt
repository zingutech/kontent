package tech.zingu.kontent.extension

import org.jetbrains.compose.web.attributes.AttrsScope
import org.w3c.dom.Element

fun AttrsScope<Element>.role(value: String) =
    attr("role", value)

fun AttrsScope<Element>.ariaHidden(value: String) =
    attr("aria-hidden", value)

fun AttrsScope<Element>.ariaModal(value: String) =
    attr("aria-modal", value)

fun AttrsScope<Element>.ariaExpanded(value: String) =
    attr("aria-expanded", value)

fun AttrsScope<Element>.ariaHasPopup(value: String) =
    attr("aria-haspopup", value)

fun AttrsScope<Element>.ariaOrientation(value: String) =
    attr("aria-orientation", value)

fun AttrsScope<Element>.ariaLabelledBy(value: String) =
    attr("aria-labelledby", value)
