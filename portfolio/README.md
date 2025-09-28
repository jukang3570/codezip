# Portfolio GitHub Pages 배포 가이드

이 폴더에는 정적 포트폴리오 사이트(`index.html`, `styles.css`)가 들어 있습니다. 아래 단계를 통해 GitHub Pages(https://pages.github.com)를 사용하여 포트폴리오를 배포하고, `git.io` 단축 URL까지 연결할 수 있습니다.

## 1. GitHub Pages 활성화
1. 이 저장소를 GitHub에 푸시합니다.
2. GitHub 저장소 페이지에서 **Settings → Pages** 메뉴로 이동합니다.
3. **Build and deployment** 섹션에서 **Source**를 `GitHub Actions`로 선택합니다. (저장하면 GitHub가 Pages용 권한을 생성합니다.)

## 2. Actions 워크플로 실행
이 커밋에는 `.github/workflows/deploy-portfolio.yml` 워크플로가 포함되어 있어 `main` 브랜치에 변경 사항이 push될 때마다 `portfolio/` 폴더 내용을 GitHub Pages에 배포합니다.

1. `main` 브랜치에 변경 사항을 push하면 워크플로가 자동으로 실행됩니다.
2. **Actions** 탭에서 `Deploy portfolio to GitHub Pages` 워크플로를 선택하고 실행 상태를 확인하세요.
3. 워크플로가 성공하면 **Pages** 설정 화면에서 배포된 사이트 URL을 확인할 수 있습니다.

> 워크플로를 수동으로 실행하고 싶다면 **Actions → Deploy portfolio to GitHub Pages → Run workflow**를 클릭하세요.

## 3. git.io 단축 URL 만들기 (선택 사항)
1. GitHub Pages 배포 URL (예: `https://<username>.github.io/<repository>/`)을 복사합니다.
2. [https://git.io](https://git.io)에 접속하여 URL Shortener 입력창에 위 URL을 붙여 넣습니다.
3. 원하는 slug(예: `myportfolio`)를 입력하고 **Shorten**을 누르면 `https://git.io/myportfolio` 형태의 단축 주소가 생성됩니다.

이제 `portfolio/`의 정적 웹사이트를 GitHub Pages와 git.io 단축 링크를 통해 손쉽게 공유할 수 있습니다.
