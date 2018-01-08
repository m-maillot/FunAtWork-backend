package io.funatwork.action.input

data class RegisterInput(val mail: String, val pseudo: String, val avatar: String?, val organizationName: String) {
    companion object {
        fun empty() = RegisterInput("sample@mail.com", "pseudo", "avatar_url", "organization")
    }
}
