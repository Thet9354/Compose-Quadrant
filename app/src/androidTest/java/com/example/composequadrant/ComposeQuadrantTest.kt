package com.example.composequadrant

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class ComposeQuadrantTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun textExistTest() {
        composeTestRule.setContent { MainActivity() }

        composeTestRule.onNodeWithText(R.string.first_title.toString())
        composeTestRule.onNodeWithText(R.string.first_description.toString())
        composeTestRule.onNodeWithText(R.string.second_title.toString())
        composeTestRule.onNodeWithText(R.string.second_description.toString())

        composeTestRule.onRoot().printToLog("TAG")

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("TAG")

    }
}
