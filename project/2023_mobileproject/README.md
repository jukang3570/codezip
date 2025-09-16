<div align="center">
  <h1>2023 모바일프로그래밍 프로젝트</h1>
  <h2>도시열섬 피해를 막기 위한 공유우산 서비스</h2>
  <p>
    🏆 2023 전북대학교 컴퓨터인공지능학부 작품경진대회 은상<br>
    🏆 2023 전북대학교 &amp; 전북비전대학교 공간경진 AI 대회 대상
  </p>
</div>

## 프로젝트 개요
- 폭염 및 국지성 호우 시 시민들이 안전하게 이동할 수 있도록 공유 우산, 물차, 무더위 쉼터 정보를 통합 제공하는 안드로이드 애플리케이션입니다.
- 실시간 지도, 지역 커뮤니티, 데이터 시각화 기능을 하나의 내비게이션 구조로 통합해 재난 대응을 돕습니다.

## 기술 스택
- **개발 환경**: Android Studio, Java
- **지도/위치**: Google Maps SDK for Android, Custom Marker Icons, VectorChildFinder
- **데이터 관리**: SQLite(`DBHelper`), ViewModel/LiveData(`SharedUmbrellaViewModel`), 내부 저장소 이미지 처리
- **외부 연동**: 서울시 무더위 쉼터 Open API(XML 파싱)

## 주요 기능
- **공유 우산 지도**: `Shared_UmbrellaFragment`에서 Google Map과 ViewModel을 활용해 우산 거점별 보유 수량을 실시간 마커로 표시하고, 위치 추가·이동 시 상태를 유지합니다.
- **열섬/기상 정보 시각화**: `HeatIslandMapFragment`가 Spinner 선택에 따라 온도·습도·열섬 지도를 벡터 레이어로 교체하여 사용자에게 상황별 정보를 제공합니다.
- **무더위 쉼터 탐색**: `HeatShelterAPI`가 서울시 Open API를 호출해 쉼터 좌표와 시설 정보를 파싱하고 지도에 마커로 전달합니다.
- **커뮤니티 게시판**: `CommunityScreenFragment`와 `DBHelper`가 SQLite 기반으로 게시글/댓글을 관리하며, 지역·테마 필터와 검색 기능을 지원합니다.
- **추가 모듈**: 물차 배치 현황, 커뮤니티 투표 등 여러 Fragment를 BottomNavigationView로 탐색합니다.

## 배포 및 기타
- VectorChildFinder 라이브러리를 활용해 SVG 자산을 파싱하고, Google Play 서비스 권한 처리 흐름을 구현했습니다.
- `final/` 디렉터리에 시연 자료 및 산출물을 정리해 프로젝트 이력을 관리합니다.
