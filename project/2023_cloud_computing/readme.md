# AWS WEB Deploy 방식의 온라인 미술관 Art Park 🎨

## 프로젝트 개요
- 온라인 전시 공간을 제공하는 Django 기반 웹 서비스로, AWS 환경에서 확장성과 안정성을 확보한 프로젝트입니다.
- 전시관·작가 페이지, 관람객 참여 기능을 통합해 실제 오프라인 미술관의 관람 동선을 그대로 웹으로 옮겼습니다.

## 기술 스택
- **Front-end**: Django Template, HTML, CSS, JavaScript 정적 자원 관리
- **Back-end**: Django 4.x, Django ORM, PyMySQL(MySQL 연동), SQLite(로컬 개발)
- **인프라**: AWS EC2 배포, Auto Scaling + Load Balancing 구성
- **기타**: Django EmailMessage 기반 SMTP 메일 발송, AWS 환경 로그 모니터링

## 주요 기능
- **온라인 전시 체험**: 3개의 기획전이 페이지 단위로 구성되어 있으며 작품별 관람 수(좋아요)를 Django 모델(`Post`)로 집계합니다.
- **관람객 피드백**: 전시 섹션별로 댓글을 남길 수 있는 코멘트 보드(`Comment` 모델)를 제공해 실시간 의견을 수집합니다.
- **작가별 상세 소개**: 작가 프로필과 작품 설명을 별도 템플릿으로 구성하여 전시 경험을 풍부하게 전달합니다.
- **이메일 문의**: 관람객이 문의를 남기면 SMTP를 통해 안내 메일을 자동 발송합니다.

## 운영 및 배포 포인트
- AWS Auto Scaling과 Load Balancing을 적용하여 관람자가 몰리는 상황에서도 안정적으로 전시를 제공할 수 있도록 구성했습니다.
- 정적 자산은 Django `STATICFILES_DIRS` 설정으로 관리하며, 환경 변수 기반으로 데이터베이스·메일 설정을 교체할 수 있게 설계했습니다.
