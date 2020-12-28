      let appListModal =  document.querySelector("#app-list-modal");
      let appDetailModal = document.querySelector("#app-detail-modal");

      // 새로운 앱 추가하기 버튼
      document.querySelector('#add-new-app-btn').addEventListener('click', function() {
        appListModal.classList.add('is-active');
      });

      // 앱 리스트 모달 닫기 버튼
      document.querySelector('#app-list-modal-close-btn').addEventListener('click', function() {
        appListModal.classList.remove('is-active');
      });

      // 앱 디테일 모달 뒤로가기 버튼
      document.querySelector('#app-detail-modal-back-btn').addEventListener('click', function() {
        appDetailModal.classList.remove('is-active');
      });

      // 앱 디테일 모답 닫기 버튼
      document.querySelector('#app-detail-modal-close-btn').addEventListener('click', function() {
        appListModal.classList.remove('is-active');
        appDetailModal.classList.remove('is-active');
      });

      let appListTagRadios = document.querySelectorAll('input[name=app-list-tag]');
      Array.prototype.forEach.call(appListTagRadios, function(radio) {
        radio.addEventListener('change', appListTagChangeHandler);
      });

      function appListTagChangeHandler(){
        console.log(this.value);
        axios.get('/apis/apps?tag=' + this.value)
          .then(function (response) {
            // handle success
            console.log(response);
            let appListWrapper = document.querySelector('.app-list');
            appListWrapper.innerHTML = '';
            renderUrlRedirection();
            renderAppList(response.data);
          })
          .catch(function (error) {
            // handle error
            console.log(error);
          })
          .then(function () {
            // always executed
          });
      }

      function renderUrlRedirection(){
        let appListWrapper = document.querySelector('.app-list');
        let urlRedirectionWrapper = document.createElement('div');
        urlRedirectionWrapper.classList.add('btn');
        urlRedirectionWrapper.classList.add('app-url-item');
        urlRedirectionWrapper.classList.add('btn');

        let iconWrapper = document.createElement('div');
        iconWrapper.classList.add('app-icon');
        let iconSvg = document.createElement('img')
        iconSvg.setAttribute('src', '/images/ico-link.svg');
        iconSvg.setAttribute('width', 42);
        iconSvg.setAttribute('alt', '');

        iconWrapper.append(iconSvg);

        let appNameWrapper = document.createElement('div');
        appNameWrapper.classList.add('app-name');
        let appNameText = document.createElement('div');
        appNameText.classList.add('app-name-text');
        appNameText.innerText = 'URL Redirection';

        appNameWrapper.append(appNameText);

        urlRedirectionWrapper.appendChild(iconWrapper);
        urlRedirectionWrapper.appendChild(appNameWrapper);

        appListWrapper.prepend(urlRedirectionWrapper);
      }

      function renderAppList(apps) {
        let appListWrapper = document.querySelector('.app-list');
        for (let app of apps) {
            console.log(app);

            let appWrapper = document.createElement('div');
            appWrapper.classList.add('btn');
            appWrapper.classList.add('app-item');

            let iconWrapper = document.createElement('div');
            let iconImage = document.createElement('img');
            iconImage.setAttribute('src', '/images/' + app[3]);
            iconImage.setAttribute('width', 42);
            iconImage.setAttribute('alt', '');
            iconWrapper.append(iconImage);

            let appNameWrapper = document.createElement('div');
            appNameWrapper.classList.add('app-name');

            let appNameText = document.createElement('div');
            appNameText.classList.add('app-name-text')
            appNameText.innerText = app[1];

            appNameWrapper.append(appNameText);

            let connectStatus = document.createElement('div');
            connectStatus.classList.add('app-status-text');
            connectStatus.innerText = 'connected';

            appNameWrapper.append(connectStatus);

            let connectStatusIconWrapper = document.createElement('div');
            connectStatusIconWrapper.classList.add('app-status-icon');
            let connectSvg = document.createElement('svg');
            connectSvg.setAttribute('viewBox', '0 0 32 24');
            connectSvg.setAttribute('width', 10);
            connectSvg.setAttribute('xmlns', 'http://www.w3.org/2000/svg');
            let svgPath = document.createElement('path');
            svgPath.setAttribute('d', 'M10.8686 23.5286L0.46861 13.0688C-0.156203 12.4404 -0.156203 11.4215 0.46861 10.7931L2.7313 8.51731C3.35611 7.88884 4.36924 7.88884 4.99405 8.51731L12 15.5635L27.0059 0.471304C27.6308 -0.157101 28.6439 -0.157101 29.2687 0.471304L31.5314 2.74707C32.1562 3.37547 32.1562 4.39436 31.5314 5.02283L13.1314 23.5287C12.5065 24.1571 11.4934 24.1571 10.8686 23.5286Z');

            connectSvg.append(svgPath);
            connectStatusIconWrapper.append(connectSvg);

            appNameWrapper.append(connectStatusIconWrapper);

            appWrapper.append(iconWrapper);
            appWrapper.appendChild(appNameWrapper);
            appListWrapper.appendChild(appWrapper);
        }
      }


      /** 아래 코드는 손 봐야 하는 코드, 위는 작업 중인 코드 **/

      function togglePopup(target, current) {
        target.classList.toggle("is-active");
        if(current) {
          current.classList.remove("is-active");
        }
      }

      // Mock Interaction - Shortcut List 아이템 유무에 따른 화면 전환.
      function shortcutListState(state) {
        let viewEmptyList = document.querySelector('.list-shortcuts-empty');
        let viewShortcutList = document.querySelector('.list-shortcuts');

        if(state === 'empty') {
          viewEmptyList.classList.remove('hidden');
          viewShortcutList.classList.add('hidden');
        } else if(state === 'notEmpty') {
          viewEmptyList.classList.add('hidden');
          viewShortcutList.classList.remove('hidden');
        }
      }

      // Mock Interaction - 앱 디테일 페이지에서 Connect / Disconnect 버튼을 눌렀을 때의 상태 변경
      function changeAppConnectivity(state) {
        if(state === 'connect') {
          let targets = ['appDetailPopup', 'asanaInAppList', 'asanaInConnectedAppList'];
          targets.forEach(target => target.classList.toggle('is-connected'));
        } else if(state === 'disconnect') {
          let disconnect = confirm('If you disconnect Asana, all the Asana shortcuts will be deleted. Are you sure to continue to disconnect?');
          if (disconnect) {
            let targets = ['appDetailPopup', 'asanaInAppList', 'asanaInConnectedAppList'];
            targets.forEach(target => target.classList.toggle('is-connected'));
            appShortcutItem.classList.add('hidden');
            // 숏컷 리스트의 상태를 확인하자.
            let shortcutList = document.querySelectorAll('.list-shortcuts .hidden');

            if(shortcutList.length === 3) {
              shortcutListState('empty');
            } else {
              shortcutListState('notEmpty');
            }
          }
        }
      }

      // Mock Interaction - 앱 디테일 페이지에서 Add To Shortcut 버튼을 눌렀을 때
      function whenClickAddShortcutButton(type) {
        // Empty 상태이던 목록에 새로운 아이템이 추가된다. 해당 아이템은 is-editing 상태
        // 원래는 Add to shortcut 버튼을 누를 때 마다 새로운 숏컷 아이템이 추가되어야 하지만 프로토타입에 포함되지 않았음.
        shortcutListState('notEmpty');
        makeShortcutItemEditable(type);

        if(type === 'app') {
          let inputAppShortcutKeyword = appShortcutItem.querySelector('.shortcut-keyword');
          let saveButton = appShortcutItem.querySelector('.btn-save');

          inputAppShortcutKeyword.textContent = '';
          saveButton.classList.add('is-disabled');

          // 앱 디테일 페이지(팝업)에서 벗어난다.
          togglePopup('appDetailPopup');
        } else if(type === 'url') {
          let inputURLShortcutKeyword = urlShortcutItem.querySelector('.shortcut-keyword');
          let inputDestinationURL = urlShortcutItem.querySelector('.destination-url');
          let saveButton = urlShortcutItem.querySelector('.btn-save');

          inputURLShortcutKeyword.textContent = '';
          inputDestinationURL.textContent = '';
          saveButton.classList.add('is-disabled');

          // 앱 목록(팝업)에서 벗어난다.
          togglePopup('appListPopup');
        }
      }

      // Mock Interaction - 숏컷 아이템 내용 수정 가능한 상태로 변경
      function makeShortcutItemEditable(type) {
        if(type === 'app') {
          appShortcutItem.classList.add('is-editing');
          appShortcutItem.classList.remove('hidden');

          let inputAppShortcutKeyword = appShortcutItem.querySelector('.shortcut-keyword');

          // 수정 가능 상태에서는 키워드 입력창의 contenteditable 값이 true.
          inputAppShortcutKeyword.setAttribute('contenteditable', true);
          inputAppShortcutKeyword.focus();

        } else if(type === 'url') {
          urlShortcutItem.classList.add('is-editing');
          urlShortcutItem.classList.remove('hidden');

          let inputURLShortcutKeyword = urlShortcutItem.querySelector('.shortcut-keyword');
          let inputDestinationURL = urlShortcutItem.querySelector('.destination-url');

          inputDestinationURL.setAttribute('contenteditable', true);
          inputURLShortcutKeyword.setAttribute('contenteditable', true);
          inputURLShortcutKeyword.focus();
        }
        // 수정 가능 상태의 아이템이 활성화 되어 있을 경우 다른 버튼들은 숨겨주자.
        addNewAppButton.classList.add('hidden');

        // connected Apps에 보여지는 샘플 앱의 onclick 이벤트 무효화
        githubInConnectedAppList.style.pointerEvents = 'none';
        asanaInConnectedAppList.style.pointerEvents = 'none';
      }

      // Mock Interaction - 숏컷 아이템 내용 수정 불가한 상태로 변경
      function makeShortcutItemDiseditable(type) {
        if(type === 'app') {
          appShortcutItem.classList.remove('is-editing');

          let inputAppShortcutKeyword = appShortcutItem.querySelector('.shortcut-keyword');
          inputAppShortcutKeyword.setAttribute('contenteditable', false);
        } else if (type === 'url') {
          urlShortcutItem.classList.remove('is-editing');

          let inputURLShortcutKeyword = urlShortcutItem.querySelector('.shortcut-keyword');
          let inputDestinationURL = urlShortcutItem.querySelector('.destination-url');

          inputDestinationURL.setAttribute('contenteditable', false);
          inputURLShortcutKeyword.setAttribute('contenteditable', false);
        }
        // 숏컷 리스트의 상태를 확인하자.
        let shortcutList = document.querySelectorAll('.list-shortcuts .hidden');

        if(shortcutList.length === 3) {
          shortcutListState('empty');
        } else {
          shortcutListState('notEmpty');
        }

        // 기본 상태로 변경 될 때 다른 버튼을 사용가능하게 바꿔주자.
        addNewAppButton.classList.remove('hidden');

        // connected Apps에 보여지는 샘플 앱의 onclick 이벤트 되돌리기
        githubInConnectedAppList.style.pointerEvents = 'auto';
        asanaInConnectedAppList.style.pointerEvents = 'auto';
      }

      // Mock Interaction - 숏컷 목록 아이템(수정 가능 상태)의 키워드 입력창에 1글자 이상의 단어를 입력할 때
      let inputAppShortcutKeyword = appShortcutItem.querySelector('.shortcut-keyword');
      let inputURLShortcutKeyword = urlShortcutItem.querySelector('.shortcut-keyword');
      let inputURLShortcutDestination = urlShortcutItem.querySelector('.destination-url');

      // 앱 숏컷 아이템 입력 validation
      inputAppShortcutKeyword.addEventListener("keyup", e => {
        if (inputAppShortcutKeyword.textContent.length > 0) {
          let target = appShortcutItem.querySelector('.btn-save');
          target.classList.remove('is-disabled');
        } else {
          let saveButton = appShortcutItem.querySelector('.btn-save');
          saveButton.classList.add('is-disabled');
        }
      });

      // URL 숏컷 아이템 입력 validation
      inputURLShortcutKeyword.addEventListener("keyup", e => {
        if (inputURLShortcutKeyword.textContent.length > 0
            && inputURLShortcutDestination.textContent.length > 3) {
          let target = urlShortcutItem.querySelector('.btn-save');
          target.classList.remove('is-disabled');
        } else {
          let saveButton = urlShortcutItem.querySelector('.btn-save');
          saveButton.classList.add('is-disabled');
        }
      });
      inputURLShortcutDestination.addEventListener("keyup", e => {
        if (inputURLShortcutKeyword.textContent.length > 0
            && inputURLShortcutDestination.textContent.length > 3) {
          let target = urlShortcutItem.querySelector('.btn-save');
          target.classList.remove('is-disabled');
        } else {
          let saveButton = urlShortcutItem.querySelector('.btn-save');
          saveButton.classList.add('is-disabled');
        }
      });


      // Mock Interaction - 숏컷 아이템을 저장할 때
      function whenClickSaveItemButton(type) {
        if(type === 'app') {
          let inputAppShortcutKeyword = appShortcutItem.querySelector('.shortcut-keyword');
          let value = inputAppShortcutKeyword.textContent;
          let length = value.length;
          let computedValue = value.replace(/\s+/g, '-').replace(/[^A-Za-z0-9_-]/g, '').toLowerCase();
          let printedShortcutURL = appShortcutItem.querySelector('.shortcut-url-with-keyword');

          if(length === 0) {
            return false;
          } else {
            // 입력된 값을 체크하여 저장 가능한 형태로 변경한다.
            inputAppShortcutKeyword.textContent = printedShortcutURL.textContent = computedValue;
            hrefShortcutURL.setAttribute('href', 'https://my.new/' + computedValue);

            if (computedValue.length > 0) {
              makeShortcutItemDiseditable('app');
            } else {
              inputAppShortcutKeyword.focus();

              let saveButton = appShortcutItem.querySelector('.btn-save');
              saveButton.classList.add('is-disabled');

              return false;
            }
          }
        } else if (type === 'url') {
          let inputURLShortcutKeyword = urlShortcutItem.querySelector('.shortcut-keyword');
          let inputURLShortcutDestination = urlShortcutItem.querySelector('.destination-url');
          let printedShortcutURL = urlShortcutItem.querySelector('.shortcut-url-with-keyword');
          let hrefShortcutURL = urlShortcutItem.querySelector('a');

          let keyword = inputURLShortcutKeyword.textContent;
          let destinationUrl = inputURLShortcutDestination.textContent;

          let computedKeyword = keyword.replace(/\s+/g, '-').replace(/[^A-Za-z0-9_-]/g, '').toLowerCase();
          let URLValidator = true // URL Validation 추가해야 함.

          // 입력된 값을 체크하여 저장 가능한 형태로 변경한다.
          inputURLShortcutKeyword.textContent = printedShortcutURL.textContent = computedKeyword;
          hrefShortcutURL.setAttribute('href', 'https://my.new/' + computedKeyword);

          if (keyword.length > 0 && destinationUrl.length > 3 && URLValidator === true) {
            makeShortcutItemDiseditable('url');
          } else {
            inputURLShortcutKeyword.focus();

            let saveButton = urlShortcutItem.querySelector('.btn-save');
            saveButton.classList.add('is-disabled');

            return false;
          }
        }
      }

      function whenClickDeleteItemButton(type) {
        if(type === 'app') {
          appShortcutItem.classList.add('hidden');
        } else if(type === 'url') {
          urlShortcutItem.classList.add('hidden');
        } else {
          return;
        }
        makeShortcutItemDiseditable(type);
      }

      // Mock Interaction - 숏컷 아이템을 수정할 때
      function whenClickEditItemButton(type) {
        makeShortcutItemEditable(type)
      }