const URL_EXPRESSION = /[-a-zA-Z0-9@:%._+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b([-a-zA-Z0-9()@:%_+.~#?&/=]*)?/gi;
const URL_REGEX = new RegExp(URL_EXPRESSION);

const ALPHA_EXPRESSION = /[a-zA-Z]{2,}/gi;
const ALPHA_REGX = new RegExp(ALPHA_EXPRESSION);

function urlShortcutItemComponent() {
    let wrapper = document.createElement('div');
    wrapper.classList.add('item-url-shortcut');

    let shortcutDiv = document.createElement('div');
    shortcutDiv.classList.add('shortcut');

    let keywordDiv = document.createElement('div');
    keywordDiv.addEventListener('keyup', function () {
        if (destinationUrlDiv.textContent.match(URL_REGEX) && this.textContent.match(ALPHA_REGX)) {
            urlShortcutSaveBtnDiv.classList.remove('is-disabled');
            return;
        }

        urlShortcutSaveBtnDiv.classList.add('is-disabled');
    });
    keywordDiv.classList.add('shortcut-keyword');
    keywordDiv.setAttribute('contenteditable', 'true');
    keywordDiv.setAttribute('data-placeholder', 'New shortcut here...');

    let shortcutUrlDiv = document.createElement('div');
    shortcutUrlDiv.classList.add('shortcut-url');

    let anchor = document.createElement('a');
    anchor.href = '#';
    anchor.target = '_blank';
    anchor.classList.add('link-outer');

    let fSpan = document.createElement('span');
    let logoImg = document.createElement('img');
    logoImg.src = '/images/logo.svg';
    logoImg.alt = 'outlink';
    logoImg.width = 12;
    fSpan.append(logoImg);

    let myNewSpan = document.createElement('span');
    myNewSpan.textContent = 'https://my.new/';

    let keywordSpan = document.createElement('span');
    keywordSpan.classList.add('shortcut-url-with-keyword');

    anchor.append(fSpan);
    anchor.append(myNewSpan);
    anchor.append(keywordSpan);
    shortcutUrlDiv.append(anchor);
    shortcutDiv.append(keywordDiv);
    shortcutDiv.append(shortcutUrlDiv);

    let appDiv = document.createElement('div');
    appDiv.classList.add('app');

    let arrowIcoDiv = document.createElement('div');
    let arrowIco = document.createElement('img');
    arrowIco.src = '/images/ico-arrow-right.svg';
    arrowIco.width = 16;
    arrowIco.alt = '';
    arrowIcoDiv.append(arrowIco);
    appDiv.append(arrowIcoDiv);

    let connectedAppDiv = document.createElement('div');
    connectedAppDiv.classList.add('connected-app');
    let appIconDiv = document.createElement('div');
    appIconDiv.classList.add('app-icon');
    let appIconImg = document.createElement('img');
    appIconImg.src = '/images/ico-link.svg';
    appIconImg.width = 42;
    appIconImg.alt = '';
    appIconDiv.append(appIconImg);
    connectedAppDiv.append(appIconDiv);
    let appUrlDiv = document.createElement('div');
    appUrlDiv.classList.add('app-url');
    let appNameDiv = document.createElement('div');
    appNameDiv.classList.add('app-name');
    appNameDiv.textContent = 'A New URL Redirection';
    let destinationUrlDiv = document.createElement('div');

    destinationUrlDiv.addEventListener('keyup', function () {
        if (this.textContent.match(URL_REGEX) && keywordDiv.textContent.match(ALPHA_REGX)) {
            urlShortcutSaveBtnDiv.classList.remove('is-disabled');
            return;
        }

        urlShortcutSaveBtnDiv.classList.add('is-disabled');
    });
    destinationUrlDiv.classList.add('destination-url');
    destinationUrlDiv.setAttribute('contenteditable', 'true');
    destinationUrlDiv.setAttribute('data-placeholder', 'Your URL here...');
    appUrlDiv.append(appNameDiv);
    appUrlDiv.append(destinationUrlDiv);
    connectedAppDiv.append(appUrlDiv);
    appDiv.append(connectedAppDiv);

    let buttonsDiv = document.createElement('div');
    buttonsDiv.classList.add('buttons');
    let urlShortcutSaveBtnDiv = document.createElement('div');
    urlShortcutSaveBtnDiv.addEventListener('click', function () {
        console.log('save btn clicked');
        if (this.classList.contains('is-disabled')) {
            return;
        }

        // api call needed
        console.log('저장이 필요해');
        let destinationUrl = destinationUrlDiv.textContent
        let path = keywordDiv.textContent;
        axios.post('/apis/url-redirections', {path, destinationUrl}).then(function (resp) {
            console.log(resp);
            wrapper.classList.remove('is-editing');
            keywordDiv.setAttribute('contenteditable', 'false');
            destinationUrlDiv.setAttribute('contenteditable', 'false');

            // 수정 가능 상태의 아이템이 활성화 되어 있을 경우 다른 버튼들은 숨겨주자.
            document.querySelector('#add-new-app-btn').classList.remove('hidden');
        }).catch(function (err) {
            console.log(err);
        });
    });
    urlShortcutSaveBtnDiv.classList.add('btn');
    urlShortcutSaveBtnDiv.classList.add('btn-save');
    urlShortcutSaveBtnDiv.classList.add('is-disabled');
    let saveImg = document.createElement('img');
    saveImg.src = '/images/ico-btn-save.svg';
    saveImg.alt = '';
    urlShortcutSaveBtnDiv.append(saveImg);
    buttonsDiv.append(urlShortcutSaveBtnDiv);

    let urlShortcutDeleteBtnDiv = document.createElement('div');
    urlShortcutDeleteBtnDiv.addEventListener('click', function () {
        wrapper.remove();
        document.querySelector('#add-new-app-btn').classList.remove('hidden');
        console.log('delete api 가 필요해');
    });
    urlShortcutDeleteBtnDiv.classList.add('btn');
    urlShortcutDeleteBtnDiv.classList.add('btn-delete');
    let deleteImg = document.createElement('img');
    deleteImg.src = '/images/ico-trash.svg';
    deleteImg.width = 16;
    deleteImg.alt = '';
    urlShortcutDeleteBtnDiv.append(deleteImg);
    buttonsDiv.append(urlShortcutDeleteBtnDiv);

    let urlShortcutEditBtnDiv = document.createElement('div');
    urlShortcutEditBtnDiv.addEventListener('click', function () {
        wrapper.classList.add('is-editing');
        keywordDiv.setAttribute('contenteditable', 'true');
        keywordDiv.focus();
        destinationUrlDiv.setAttribute('contenteditable', 'true');

        // 수정 가능 상태의 아이템이 활성화 되어 있을 경우 다른 버튼들은 숨겨주자.
        document.querySelector('#add-new-app-btn').classList.add('hidden');

        // connected Apps에 보여지는 샘플 앱의 onclick 이벤트 무효화
        // githubInConnectedAppList.style.pointerEvents = 'none';
        // asanaInConnectedAppList.style.pointerEvents = 'none';
    });
    urlShortcutEditBtnDiv.classList.add('btn');
    urlShortcutEditBtnDiv.classList.add('btn-edit');
    let editImg = document.createElement('img');
    editImg.src = '/images/ico-pencil.svg';
    editImg.width = 16;
    editImg.alt = '';
    urlShortcutEditBtnDiv.append(editImg);
    buttonsDiv.append(urlShortcutEditBtnDiv);
    appDiv.append(buttonsDiv);

    wrapper.append(shortcutDiv);
    wrapper.append(appDiv);

    return wrapper;
}
