let appListModal = document.querySelector("#app-list-modal");
let appDetailModal = document.querySelector("#app-detail-modal");

// 새로운 앱 추가하기 버튼
document.querySelector('#add-new-app-btn').addEventListener('click', function () {
    appListTagChangeHandler();
    appListModal.classList.add('is-active');
});

// 앱 리스트 모달 닫기 버튼
document.querySelector('#app-list-modal-close-btn').addEventListener('click', function () {
    appListModal.classList.remove('is-active');
});

// 앱 디테일 모달 뒤로가기 버튼
document.querySelector('#app-detail-modal-back-btn').addEventListener('click', function () {
    appDetailModal.classList.remove('is-active');
});

// 앱 디테일 모답 닫기 버튼
document.querySelector('#app-detail-modal-close-btn').addEventListener('click', function () {
    appListModal.classList.remove('is-active');
    appDetailModal.classList.remove('is-active');
});

let appListTagRadios = document.querySelectorAll('input[name=app-list-tag]');
Array.prototype.forEach.call(appListTagRadios, function (radio) {
    radio.addEventListener('change', appListTagChangeHandler);
});

function appListTagChangeHandler() {
    console.log(this.value);
    console.log(this.value === undefined);
    let tag = this.value === undefined ? "ALL" : this.value;
    axios.get('/apis/apps?tag=' + tag)
        .then(function (response) {
            // handle success
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

function renderUrlRedirection() {
    let appListWrapper = document.querySelector('.app-list');
    let urlRedirectionWrapper = document.createElement('div');
    urlRedirectionWrapper.classList.add('btn');
    urlRedirectionWrapper.classList.add('app-url-item');
    urlRedirectionWrapper.classList.add('btn');
    urlRedirectionWrapper.addEventListener('click', function () {
        appListModal.classList.remove('is-active');
        appDetailModal.classList.remove('is-active');
        document.querySelector('.list-shortcuts').append(urlShortcutItemComponent());
    });

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
    console.log(apps);
    for (let app of apps) {
        let appWrapper = document.createElement('div');
        if (app.connected) {
            appWrapper.classList.add('is-connected')
        }
        appWrapper.classList.add('btn');
        appWrapper.classList.add('app-item');
        appWrapper.addEventListener('click', function () {
            appDetailModal.classList.add('is-active');
        });

        let iconWrapper = document.createElement('div');
        let iconImage = document.createElement('img');
        iconImage.setAttribute('src', '/images/' + app.appIcon);
        iconImage.setAttribute('width', 42);
        iconImage.setAttribute('alt', '');
        iconWrapper.append(iconImage);

        let appNameWrapper = document.createElement('div');
        appNameWrapper.classList.add('app-name');

        let appNameText = document.createElement('div');
        appNameText.classList.add('app-name-text')
        appNameText.innerText = app.appName;

        appNameWrapper.append(appNameText);

        let connectStatus = document.createElement('div');
        connectStatus.classList.add('app-status-text');
        connectStatus.innerText = 'Is connected';

        appNameWrapper.append(connectStatus);

        let connectStatusIconWrapper = document.createElement('div');
        connectStatusIconWrapper.classList.add('app-status-icon');
        let connectImg = document.createElement('img');
        connectImg.src = '/images/ico-connected.svg';

        connectStatusIconWrapper.append(connectImg);

        appNameWrapper.append(connectStatusIconWrapper);

        appWrapper.append(iconWrapper);
        appWrapper.appendChild(appNameWrapper);
        appListWrapper.appendChild(appWrapper);
    }
}




/**
 * url shortcut 수정 버튼
 */
// document.querySelector('#url-shortcut-edit-btn').addEventListener('click', function () {
//     let urlShortcutItem = document.querySelector('#urlShortcutItem');
//     urlShortcutItem.classList.add('is-editing');
//     urlShortcutItem.classList.remove('hidden');
//
//     let inputURLShortcutKeyword = urlShortcutItem.querySelector('.shortcut-keyword');
//     let inputDestinationURL = urlShortcutItem.querySelector('.destination-url');
//
//     inputDestinationURL.setAttribute('contenteditable', 'true');
//     inputURLShortcutKeyword.setAttribute('contenteditable', 'true');
//     inputURLShortcutKeyword.focus();
//
    // 수정 가능 상태의 아이템이 활성화 되어 있을 경우 다른 버튼들은 숨겨주자.
    // document.querySelector('#add-new-app-btn').classList.add('hidden');

    // connected Apps에 보여지는 샘플 앱의 onclick 이벤트 무효화
    // githubInConnectedAppList.style.pointerEvents = 'none';
    // asanaInConnectedAppList.style.pointerEvents = 'none';
// });

/**
 * url shortcut 저장 버튼
 */
// document.querySelector('#url-shortcut-save-btn').addEventListener('click', function (){
//     let urlShortcutItem = document.querySelector('#urlShortcutItem');
//     let inputURLShortcutKeyword = urlShortcutItem.querySelector('.shortcut-keyword');
//     let inputURLShortcutDestination = urlShortcutItem.querySelector('.destination-url');
//     let printedShortcutURL = urlShortcutItem.querySelector('.shortcut-url-with-keyword');
//     let hrefShortcutURL = urlShortcutItem.querySelector('a');
//
//     let keyword = inputURLShortcutKeyword.textContent;
//     let destinationUrl = inputURLShortcutDestination.textContent;
//
//     let computedKeyword = keyword.replace(/\s+/g, '-').replace(/[^A-Za-z0-9_-]/g, '').toLowerCase();
//     let URLValidator = true // URL Validation 추가해야 함.
//
    // 입력된 값을 체크하여 저장 가능한 형태로 변경한다.
    // inputURLShortcutKeyword.textContent = printedShortcutURL.textContent = computedKeyword;
    // hrefShortcutURL.setAttribute('href', 'https://my.new/' + computedKeyword);

    // if (keyword.length > 0 && destinationUrl.length > 3 && URLValidator === true) {
    //     urlShortcutItem.classList.remove('is-editing');
    //
    //     let inputURLShortcutKeyword = urlShortcutItem.querySelector('.shortcut-keyword');
    //     let inputDestinationURL = urlShortcutItem.querySelector('.destination-url');
    //
    //     inputDestinationURL.setAttribute('contenteditable', false);
    //     inputURLShortcutKeyword.setAttribute('contenteditable', false);
    // } else {
    //     document.querySelector('#urlShortcutItem .shortcut-keyword').focus();
    //
    //     let saveButton = urlShortcutItem.querySelector('.btn-save');
    //     saveButton.classList.add('is-disabled');
    //
        // 숏컷 리스트의 상태를 확인하자.
        // let shortcutList = document.querySelectorAll('.list-shortcuts .hidden');

        // if (shortcutList.length === 3) {
        //     shortcutListState('empty');
        // } else {
        //     shortcutListState('notEmpty');
        // }

        // 기본 상태로 변경 될 때 다른 버튼을 사용가능하게 바꿔주자.
        // document.querySelector('#add-new-app-btn').classList.remove('hidden');

        // connected Apps에 보여지는 샘플 앱의 onclick 이벤트 되돌리기
        // githubInConnectedAppList.style.pointerEvents = 'auto';
        // asanaInConnectedAppList.style.pointerEvents = 'auto';
    // }
// });

// document.querySelector('#url-shortcut-delete-btn').addEventListener('click', function () {
//     let urlShortcutItem = document.querySelector('#urlShortcutItem');
//     urlShortcutItem.classList.add('hidden');
//     document.querySelector('#urlShortcutItem .shortcut-keyword').focus();
//
//     let saveButton = urlShortcutItem.querySelector('.btn-save');
//     saveButton.classList.add('is-disabled');

    // 숏컷 리스트의 상태를 확인하자.
    // let shortcutList = document.querySelectorAll('.list-shortcuts .hidden');

    // if (shortcutList.length === 3) {
    //     shortcutListState('empty');
    // } else {
    //     shortcutListState('notEmpty');
    // }

    // 기본 상태로 변경 될 때 다른 버튼을 사용가능하게 바꿔주자.
    // document.querySelector('#add-new-app-btn').classList.remove('hidden');

    // connected Apps에 보여지는 샘플 앱의 onclick 이벤트 되돌리기
    // githubInConnectedAppList.style.pointerEvents = 'auto';
    // asanaInConnectedAppList.style.pointerEvents = 'auto';
// });