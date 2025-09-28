package com.example.kidschatting

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Start 버튼 설정 및 클릭 이벤트 추가
        val startStoryButton: ImageButton = findViewById(R.id.btn_start_story)
        startStoryButton.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }

        // 메인 이미지와 텍스트 업데이트를 위한 뷰 연결
        val mainStoryImage: ImageView = findViewById(R.id.main_story_image)
        val mainTitle: TextView = findViewById(R.id.tv_welcome)

        // 동화 데이터 설정
        val storyData = listOf(
            StoryItem(
                R.id.story_image_1,
                R.drawable.story,
                "용감한 남매가 호랑이를 피해 펼치는 짜릿한 모험 이야기"
            ),
            StoryItem(
                R.id.story2,
                R.drawable.story2,
                "정직한 나무꾼의 금도끼와 은도끼 이야기"
            ),
            StoryItem(
                R.id.story3,
                R.drawable.story3,
                "느림보 거북이와 방심한                                                        토끼의 교훈적인 경주 이야기"
            ),
            StoryItem(
                R.id.story4,
                R.drawable.story4,
                "마법 같은 모자, 도깨비 감투! 쓰면 아무도 나를 볼 수 없답니다!"
            ),
            StoryItem(
                R.id.story5,
                R.drawable.story5,
                "느릿느릿 거북이와 재빠른 원숭이! 서로 다른 친구들이 힘을 합쳐 해결하는 놀라운 이야기!"
            ),
            StoryItem(
                R.id.story6,
                R.drawable.story6,
                "착한 마음씨가 가져온 기적! 도깨비와 함께 혹을 없앤 할아버지의 흥미로운 이야기."
            ),
            StoryItem(
                R.id.story7,
                R.drawable.story7,
                "효심 가득한 심청이, 부모를 위한 사랑이                                      세상을 밝힙니다!"
            ),
            StoryItem(
                R.id.story8,
                R.drawable.story8,
                "사과를 받은 백설공주! 과연 무슨 일이 벌어질까요?"
            )
        )

        // 각 동화 클릭 이벤트 설정
        storyData.forEach { story ->
            val cardView: CardView = findViewById(story.cardViewId)
            cardView.setOnClickListener {
                updateMainStory(mainStoryImage, mainTitle, story)
            }
        }
    }

    /**
     * 메인 스토리 이미지와 텍스트를 업데이트하는 함수
     */
    private fun updateMainStory(imageView: ImageView, textView: TextView, storyItem: StoryItem) {
        imageView.setImageResource(storyItem.imageResId)
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER
        textView.text = storyItem.description
    }

    /**
     * 동화 데이터 클래스
     */
    data class StoryItem(
        val cardViewId: Int, // CardView ID
        val imageResId: Int, // 이미지 리소스 ID
        val description: String // 텍스트 설명
    )
}
