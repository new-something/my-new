const KEYWORD_EXPRESSION = /^[a-z]{2,}(\/[a-z]+)?$/gi;
const KEYWORD_REGEX = new RegExp(KEYWORD_EXPRESSION);

function appShortcutItemComponent(connectedId, providedActionDescription, appLogoSrc, providedActionId){
    let wrapper = document.createElement('div');
    wrapper.classList.add('item-shortcut');
    wrapper.classList.add('is-editing');

    let shortcut = document.createElement('div');
    shortcut.classList.add('shortcut');

    let shortcutKeywordDiv = document.createElement('div');
    shortcutKeywordDiv.classList.add('shortcut-keyword');
    shortcutKeywordDiv.setAttribute('contenteditable', 'true');
    shortcutKeywordDiv.setAttribute('data-placeholder', 'New shortcut here...');
    shortcutKeywordDiv.addEventListener('keyup', function () {
        if (this.textContent.match(KEYWORD_REGEX)) {
            console.log('드르와');
            saveBtnDiv.classList.remove('is-disabled');
        }
    });

    let shortcutUrlDiv = document.createElement('div');
    shortcutUrlDiv.classList.add('shortcut-url');

    let shortcutUrlAnchor = document.createElement('a');
    shortcutUrlAnchor.target = '_blank';
    shortcutUrlAnchor.classList.add('link-outer');

    let appLogoSpan = document.createElement('span');
    let appLogoImg = document.createElement('img');
    appLogoImg.src = '/images/logo.svg';
    appLogoImg.alt = 'outlink';
    appLogoImg.width = 12;
    appLogoSpan.append(appLogoImg);

    let myServiceHostSpan = document.createElement('span');
    myServiceHostSpan.textContent = 'https://my.new/';

    let printedShortcutUrlSpan = document.createElement('span');
    printedShortcutUrlSpan.classList.add('shortcut-url-with-keyword');

    shortcutUrlAnchor.append(appLogoSpan);
    shortcutUrlAnchor.append(myServiceHostSpan);
    shortcutUrlAnchor.append(printedShortcutUrlSpan);

    shortcutUrlDiv.append(shortcutUrlAnchor);

    shortcut.append(shortcutKeywordDiv);
    shortcut.append(shortcutUrlDiv);

    wrapper.append(shortcut);

    let app = document.createElement('div');
    app.classList.add('app');

    let icoArrowDiv = document.createElement('div');
    icoArrowDiv.classList.add('ico-arrow');
    let icoArrowImg = document.createElement('img');
    icoArrowImg.src = '/images/ico-arrow-right.svg';
    icoArrowImg.width = 16;
    icoArrowImg.alt = '';
    icoArrowDiv.append(icoArrowImg);

    let connectedAppDiv = document.createElement('div');
    connectedAppDiv.classList.add('connected-app');
    let appIconDiv = document.createElement('div');
    appIconDiv.classList.add('app-icon');
    let appIconImg = document.createElement('img');
    appIconImg.src = appLogoSrc;
    appIconImg.width = 42;
    appIconImg.alt = providedActionDescription;
    appIconDiv.append(appIconImg);
    let appNameDiv = document.createElement('div');
    appNameDiv.classList.add('app-name');
    appNameDiv.textContent = providedActionDescription;
    connectedAppDiv.append(appIconDiv);
    connectedAppDiv.append(appNameDiv);

    let buttonsDiv = document.createElement('div');
    buttonsDiv.classList.add('buttons');
    let saveBtnDiv = document.createElement('div');
    saveBtnDiv.classList.add('btn');
    saveBtnDiv.classList.add('btn-save');
    saveBtnDiv.classList.add('is-disabled');
    saveBtnDiv.addEventListener('click', function (e) {
        console.log('clicked save app shortcut btn');
        axios.post('/apis/shortcuts', {connectedId, providedActionId, shortcutKeyword : shortcutKeywordDiv.textContent}).then(function (resp) {
            console.log(resp.data);
            // refactor target : dom 구조가 깨지면 해당코드도 깨진다..
            let shortcutParentDiv = e.target.parentElement.parentElement.parentElement;
            shortcutParentDiv.classList.remove('is-editing');
            shortcutParentDiv.setAttribute('shortcut-id', resp.data.shortcutId);
            shortcutParentDiv.setAttribute('connected-id', connectedId);
        }).catch(function (err) {
            console.log(err);
        })
    });

    let saveImg = document.createElement('img');
    saveImg.src = '/images/ico-btn-save.svg';
    saveImg.alt = '';
    saveBtnDiv.append(saveImg);

    let deleteBtnDiv = document.createElement('div');
    deleteBtnDiv.classList.add('btn');
    deleteBtnDiv.classList.add('btn-delete');
    deleteBtnDiv.addEventListener('click', function () {
        console.log('clicked delete app shortcut btn');
        wrapper.remove();
        let editingElCount = 0;
        document.querySelectorAll('.list-shortcuts .item-shortcut').forEach((el) => {
           if (el.classList.contains('is-editing')) {
               editingElCount++;
           }
        });

        // edit 중인 shortcut 이 없는 경우에만 add new app 버튼 노출
        if (editingElCount === 0) {
            document.querySelector('#add-new-app-btn').classList.remove('hidden');
        }
    });
    let deleteImg = document.createElement('img');
    deleteImg.src = '/images/ico-trash.svg';
    deleteImg.width = 16;
    deleteImg.alt = '';
    deleteBtnDiv.append(deleteImg);

    buttonsDiv.append(saveBtnDiv);
    buttonsDiv.append(deleteBtnDiv);

    app.append(icoArrowDiv);
    app.append(connectedAppDiv);
    app.append(buttonsDiv);

    wrapper.append(app);

    return wrapper;
}