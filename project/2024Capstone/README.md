# 유아 발화 향상을 위한 동화 구현 생성형 AI

## 프로젝트 개요
유아가 친숙한 동화를 따라 읽고 대화를 주고받으며 자연스럽게 발화를 연습할 수 있도록 설계한 생성형 AI 학습 보조 서비스입니다. 안드로이드 애플리케이션을 통해 다양한 전래 동화를 소개하고, 선택한 이야기와 연동된 AI 챗봇과의 상호작용을 지원해 아이의 표현력을 확장시키는 것을 목표로 합니다.

## 주요 기능
- **동화 도서관 및 프리뷰 갱신**: 메인 화면에서 8편의 전래 동화를 카드 형태로 제공하고, 카드를 누르면 대표 이미지와 줄거리가 즉시 갱신되어 아이가 흥미를 느끼는 이야기를 쉽게 고를 수 있습니다.【F:project/2024Capstone/kidschatting/kidschatting/app/src/main/java/com/example/kidschatting/MainActivity.kt†L13-L78】
- **AI 챗 기반 대화 학습**: Start 버튼을 누르면 챗 화면으로 이동해 아이의 입력 메시지를 RecyclerView에 기록하고, Flask 기반 AI 서버의 응답을 받아 자연스럽게 대화를 이어갑니다.【F:project/2024Capstone/kidschatting/kidschatting/app/src/main/java/com/example/kidschatting/ChatActivity.kt†L30-L84】
- **음성 업로드 및 피드백**: 텍스트 입력 외에도 녹음 파일(mp4)을 전송하여 서버에서 발화를 분석하고 결과를 챗 목록에 다시 표시해 말하기 연습을 돕습니다.【F:project/2024Capstone/kidschatting/kidschatting/app/src/main/java/com/example/kidschatting/ChatActivity.kt†L50-L127】
- **맞춤형 음성·언어 모델 학습**: Whisper 음성 인식과 Llama 3 기반 생성형 모델을 Fine-tuning하여 아이 발화 데이터에 맞춘 응답과 평가가 가능하도록 구성했습니다.【F:project/2024Capstone/분컴_학습 및 server.ipynb†L10-L155】【F:project/2024Capstone/분컴_학습 및 server.ipynb†L287-L358】【F:project/2024Capstone/분컴_학습 및 server.ipynb†L430-L484】
- **TTS 기반 피드백 제공**: Naver TTS API 연동으로 서버에서 생성한 문장을 다시 음성으로 변환해 들려줄 수 있도록 설계했습니다.【F:project/2024Capstone/분컴_학습 및 server.ipynb†L582-L618】

## 시스템 구성
| 구성 요소 | 설명 |
| --- | --- |
| Android 클라이언트 | MainActivity에서 동화 선택, ChatActivity에서 메시지 송수신 및 파일 업로드를 담당합니다.【F:project/2024Capstone/kidschatting/kidschatting/app/src/main/java/com/example/kidschatting/MainActivity.kt†L13-L97】【F:project/2024Capstone/kidschatting/kidschatting/app/src/main/java/com/example/kidschatting/ChatActivity.kt†L24-L127】 |
| AI 백엔드 | Flask 서버가 `/chat` 엔드포인트로 텍스트·음성 요청을 처리하고, Llama 3 모델로 답변을 생성합니다.【F:project/2024Capstone/분컴_학습 및 server.ipynb†L503-L562】 |
| 음성 파이프라인 | Whisper 미세조정으로 음성을 텍스트로 변환하고, Naver TTS로 맞춤형 음성 피드백을 생성합니다.【F:project/2024Capstone/분컴_학습 및 server.ipynb†L10-L155】【F:project/2024Capstone/분컴_학습 및 server.ipynb†L582-L618】 |

## 시작하기
1. **안드로이드 클라이언트 설정**
   - `RetrofitInstance`에 배포된 서버 주소를 입력하고 빌드합니다.【F:project/2024Capstone/kidschatting/kidschatting/app/src/main/java/com/example/kidschatting/network/RetrofitInstance.kt†L8-L25】
   - Android Studio에서 `kidschatting` 모듈을 열어 에뮬레이터 또는 실제 기기에서 실행합니다.
2. **AI 서버 구동**
   - 학습된 Whisper·Llama 모델 가중치를 `분컴_학습 및 server.ipynb`에 정의된 경로 구조에 배치합니다.【F:project/2024Capstone/분컴_학습 및 server.ipynb†L142-L143】【F:project/2024Capstone/분컴_학습 및 server.ipynb†L430-L465】
   - Flask 앱을 실행하면 `/chat` 엔드포인트에서 텍스트/음성 요청을 받을 준비가 됩니다.【F:project/2024Capstone/분컴_학습 및 server.ipynb†L503-L562】

## API 요약
| 메서드 | 경로 | 설명 |
| --- | --- | --- |
| `POST` | `/chat` | JSON 본문에 `message`를 담아 전송하면 AI 답변 텍스트를 반환합니다.【F:project/2024Capstone/kidschatting/kidschatting/app/src/main/java/com/example/kidschatting/network/ChatApi.kt†L18-L24】【F:project/2024Capstone/분컴_학습 및 server.ipynb†L503-L554】 |
| `POST` | `/chat` (multipart) | `file` 파라미터에 mp4 음성 파일을 첨부하면 분석 결과 또는 피드백을 반환합니다.【F:project/2024Capstone/kidschatting/kidschatting/app/src/main/java/com/example/kidschatting/network/ChatApi.kt†L21-L24】 |

## 기대 효과 및 향후 계획
- 아이가 즐겨 읽는 동화 속 문장을 반복하며 발화 빈도와 명료도를 높일 수 있습니다.
- Whisper·Llama 파이프라인의 추가 데이터 학습 및 감정 분석 도입으로 더 세밀한 피드백을 제공할 수 있도록 확장할 예정입니다.
