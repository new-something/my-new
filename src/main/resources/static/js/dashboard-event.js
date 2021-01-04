let appListModal = document.querySelector("#app-list-modal");
let appDetailModal = document.querySelector("#app-detail-modal");

// 새로운 앱 추가하기 버튼
document.querySelector('#add-new-app-btn').addEventListener('click', function () {
    document.querySelector('#tag-all').click();
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

// 앱 디테일 모달 닫기 버튼
document.querySelector('#app-detail-modal-close-btn').addEventListener('click', function () {
    appListModal.classList.remove('is-active');
    appDetailModal.classList.remove('is-active');
});

// 앱 디테일 모달에서 앱 연동 버튼
document.querySelector('#app-connect-btn').addEventListener('click', function () {
    let appCode = document.querySelector("#app-detail-modal").getAttribute('app-code');
    axios.post('/a/apps', {appCode})
        .then(function (resp) {
            console.log(resp);
            let app = resp.data;
            let connectedApp = document.createElement('div');
            connectedApp.classList.add('ico-app');
            connectedApp.classList.add('is-connected');
            let connectedAppImg = document.createElement('img');
            connectedAppImg.src = '/images/' + app.appIcon;
            connectedAppImg.title = app.appName;
            connectedAppImg.alt = '';
            connectedAppImg.width = 48;
            connectedAppImg.setAttribute('code', app.appCode);
            connectedAppImg.setAttribute('connected-id', app.connectedId);
            connectedAppImg.setAttribute('domain', app.domain);
            connectedApp.append(connectedAppImg);
            // add new app btn 앞에 새로 연동된 앱을 insert 한다.
            document.querySelector('#connected-apps').insertBefore(connectedApp, document.querySelector('#add-new-app-btn'));
            document.querySelector('#app-detail-modal').classList.add('is-connected');

            document.querySelectorAll('#app-list-modal .app-item').forEach((el) => {
                if (+el.getAttribute('app-code') === +appCode) {
                    el.classList.add('is-connected');
                }
            });
        })
        .catch(function (error) {
            console.log(error);
        });
});

// 앱 디테일 모달에서 앱 연동 해제 버튼
document.querySelector('#app-disconnect-btn').addEventListener('click', function () {
    let disconnect = confirm('If you disconnect Asana, all the Asana shortcuts will be deleted. Are you sure to continue to disconnect?');
    if (disconnect) {
        let appCode = document.querySelector('#app-detail-modal').getAttribute('app-code');
        axios.delete('/a/apps/' + appCode).then(function (resp) {
            console.log(resp);
            document.querySelector('#app-detail-modal').classList.remove('is-connected');
            let appItems = document.querySelectorAll('#app-list-modal .app-item');
            console.log(appItems);
            appItems.forEach((el) => {
                console.log(el.getAttribute('app-code'));
                if (+el.getAttribute('app-code') === +appCode) {
                    console.log('찾았다 요놈');
                    el.classList.remove('is-connected');
                }
            });

            let connectedApps = document.querySelectorAll('#connected-apps .ico-app');
            connectedApps.forEach((el) => {
                let img = el.querySelector('img');
                if (+img.getAttribute('code') === +appCode) {
                    el.remove();
                }
            });
        }).catch(function (err) {
            console.log(err);
        });
    }
});

let appListTagRadios = document.querySelectorAll('input[name=app-list-tag]');
Array.prototype.forEach.call(appListTagRadios, function (radio) {
    radio.addEventListener('change', appListTagChangeHandler);
});

function appListTagChangeHandler() {
    console.log(this.value);
    console.log(this.value === undefined);
    let tag = this.value === undefined ? "ALL" : this.value;
    axios.get('/a/apps?tag=' + tag)
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
        appWrapper.setAttribute('app-code', app.appCode);
        if (app.connected) {
            appWrapper.classList.add('is-connected')
            appWrapper.setAttribute('connected-id', app.connectedId);
        }
        appWrapper.classList.add('btn');
        appWrapper.classList.add('app-item');
        appWrapper.setAttribute('icon-src', app.appIcon);
        appWrapper.setAttribute('app-name', app.appName);
        appWrapper.setAttribute('app-description', app.description);
        appWrapper.setAttribute('app-domain', app.domain);
        appWrapper.addEventListener('click', appItemClickHandler);

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

function appItemClickHandler() {
    console.log(this);
    console.log('드르와');
    console.log(this.classList.contains('is-connected'));
    appDetailModal.classList.remove('is-connected');
    if (this.classList.contains('is-connected')) {
        appDetailModal.classList.add('is-connected');
    }

    let appCode = this.getAttribute('app-code');
    let connectedId = this.getAttribute('connected-id');
    let appIcon = this.getAttribute('icon-src');
    let appName = this.getAttribute('app-name');
    let appDescription = this.getAttribute('app-description');
    let appDomain = this.getAttribute('app-domain');

    document.querySelector("#app-detail-modal").setAttribute('app-code', appCode);
    document.querySelector('#app-icon-in-detail-modal').src = '/images/' + appIcon;
    document.querySelector('#app-name-in-detail-modal').textContent = appName;
    document.querySelector('#app-description-in-detail-modal').textContent = appDescription;
    let appDomainAnchor = document.querySelector('#app-domain-in-detail-modal');
    appDomainAnchor.textContent = appDomain;
    appDomainAnchor.href = appDomain;


    axios.get('/a/provided-actions?app_code=' + appCode).then(function (resp) {
        console.log(resp)
        let appActionItems = document.querySelector('#app-action-items');
        appActionItems.innerHTML = ''
        let title = document.createElement('h3');
        title.textContent = 'Actions';
        appActionItems.append(title);

        let providedActions = resp.data;
        console.log(providedActions);
        for (let providedAction of providedActions) {
            console.log(providedAction);
            let action = document.createElement('div');
            action.classList.add('action-list');
            let item = document.createElement('div');
            item.classList.add('item-action');
            let connectedApp = document.createElement('div');
            connectedApp.classList.add('connected-app');
            let appIconDiv = document.createElement('div');
            appIconDiv.classList.add('app-icon');
            let appIconImg = document.createElement('img');
            appIconImg.src = '/images/' + appIcon;
            appIconImg.width = 32;
            appIconImg.alt = '';
            appIconDiv.append(appIconImg);
            let providedActionDescriptionDiv = document.createElement('div');
            providedActionDescriptionDiv.classList.add('app-name');
            providedActionDescriptionDiv.textContent = providedAction.description;
            connectedApp.append(appIconDiv);
            connectedApp.append(providedActionDescriptionDiv);
            let buttons = document.createElement('div');
            buttons.classList.add('buttons');
            let addBtn = document.createElement('div');
            addBtn.classList.add('btn');
            addBtn.classList.add('btn-add-shortcut');
            addBtn.textContent = 'Add to shortcut';
            addBtn.addEventListener('click', function() {
                addAppShortcutHandler(connectedId, providedAction.description, '/images/' + appIcon, providedAction.providedActionId);
            });
            buttons.append(addBtn);
            item.append(connectedApp);
            item.append(buttons);
            action.append(item);
            appActionItems.append(action);
        }
        appDetailModal.classList.add('is-active');
    }).catch(function (err) {
        console.log(err);
    });
}

// 연동한 앱의 shortcut 추가 버튼
function addAppShortcutHandler(connectedId, providedActionDescription, appIcon, providedActionId) {
    console.log('add app shortcut handler');
    let appShortcutComponent = appShortcutItemComponent(connectedId, providedActionDescription, appIcon, providedActionId);
    document.querySelector('.list-shortcuts').append(appShortcutComponent);
    document.querySelector('#add-new-app-btn').classList.add('hidden');
    appListModal.classList.remove('is-active');
    appDetailModal.classList.remove('is-active');
}

// 연동 된 앱 클릭 시 디테일 모달 오픈.
function openConnectedAppDetailModal(appDetail){
    console.log(appDetail);
    console.log('열려라 디테일 모달!');
    appDetailModal.classList.add('is-active');
    appDetailModal.classList.add('is-connected');

    let appCode = appDetail.getAttribute('app-code');
    let connectedId = appDetail.getAttribute('connected-id');
    let appSrc = appDetail.getAttribute('src');
    let appName = appDetail.getAttribute('title');
    let appDescription = appDetail.getAttribute('description');
    let appDomain = appDetail.getAttribute('domain');

    document.querySelector("#app-detail-modal").setAttribute('app-code', appCode);
    document.querySelector('#app-icon-in-detail-modal').src = appSrc;
    document.querySelector('#app-name-in-detail-modal').textContent = appName;
    document.querySelector('#app-description-in-detail-modal').textContent = appDescription;
    let appDomainAnchor = document.querySelector('#app-domain-in-detail-modal');
    appDomainAnchor.textContent = appDomain;
    appDomainAnchor.href = appDomain;


    axios.get('/a/provided-actions?app_code=' + appCode).then(function (resp) {
        console.log(resp)
        let appActionItems = document.querySelector('#app-action-items');
        appActionItems.innerHTML = ''
        let title = document.createElement('h3');
        title.textContent = 'Actions';
        appActionItems.append(title);

        let providedActions = resp.data;
        console.log(providedActions);
        for (let providedAction of providedActions) {
            let action = document.createElement('div');
            action.classList.add('action-list');
            let item = document.createElement('div');
            item.classList.add('item-action');
            let connectedApp = document.createElement('div');
            connectedApp.classList.add('connected-app');
            let appIconDiv = document.createElement('div');
            appIconDiv.classList.add('app-icon');
            let appIconImg = document.createElement('img');
            appIconImg.src = appSrc;
            appIconImg.width = 32;
            appIconImg.alt = '';
            appIconDiv.append(appIconImg);
            let providedActionDescriptionDiv = document.createElement('div');
            providedActionDescriptionDiv.classList.add('app-name');
            providedActionDescriptionDiv.textContent = providedAction.description;
            connectedApp.append(appIconDiv);
            connectedApp.append(providedActionDescriptionDiv);
            let buttons = document.createElement('div');
            buttons.classList.add('buttons');
            let addBtn = document.createElement('div');
            addBtn.classList.add('btn');
            addBtn.classList.add('btn-add-shortcut');
            addBtn.textContent = 'Add to shortcut';
            addBtn.addEventListener('click', function (){
                addAppShortcutHandler(connectedId, providedAction.description, appSrc, providedAction.providedActionId);
            });
            buttons.append(addBtn);
            item.append(connectedApp);
            item.append(buttons);
            action.append(item);
            appActionItems.append(action);
        }
        appDetailModal.classList.add('is-active');
    }).catch(function (err) {
        console.log(err);
    });
}